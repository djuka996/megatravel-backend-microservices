package com.megatravel.zuulsvr.filters;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreFilter extends ZuulFilter {
	
	private static final String SOAP_PORT = "soap-port";
	private static final String SOAP_PREFIX = "/services";
	
	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private ZuulProperties properties;
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";
	}
	
	@Override
	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		String requestURL = this.getFullURL(request);
		if(requestURL.contains(SOAP_PREFIX)) {
			String path = this.getZuulPathFromURL(requestURL);
			String microserviceName = this.getServiceNameFromRoute(path);
			List<ServiceInstance> microserviceInstances = discoveryClient.getInstances(microserviceName);
			int numberOfInstances = microserviceInstances.size();
			if(numberOfInstances > 0) {
				Random randomizer = new Random();
				int instanceIndex = randomizer.nextInt(numberOfInstances);
				ServiceInstance microserviceInstance = microserviceInstances.get(instanceIndex);
				String soapServicePort = microserviceInstance.getMetadata().get(SOAP_PORT);
				String soapServiceEndpoint = this.getSoapServiceEndpointFromUrl(requestURL);
				String serviceFullAddress = "http://localhost:" + soapServicePort + soapServiceEndpoint;
				try {
					context.setSendZuulResponse(false);
					context.setResponseStatusCode(HttpStatus.SC_TEMPORARY_REDIRECT);
					context.getResponse().sendRedirect(serviceFullAddress);
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			}
		}
	    return null;
	}

	private String getSoapServiceEndpointFromUrl(String url) {
		String endpoint = url.substring(url.indexOf(SOAP_PREFIX));
		return endpoint;
	}
	
	private String getZuulPathFromURL(String url) {
		String route = url.substring(0, url.indexOf(SOAP_PREFIX));
		route = route.substring(route.lastIndexOf("/") + 1);
		return "/" + route + "/**";
	}
	
	private String getServiceNameFromRoute(String path) {
		Collection<ZuulRoute> routes = properties.getRoutes().values();
		for(ZuulRoute route : routes) {
			if(route.getPath().equals(path)) {
				return route.getServiceId();
			}
		}
		return null;
	}
	
	private String getFullURL(HttpServletRequest request) {
	    StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
	    String queryString = request.getQueryString();
	    if (queryString == null) {
	        return requestURL.toString();
	    } else {
	        return requestURL.append('?').append(queryString).toString();
	    }
	}

}
