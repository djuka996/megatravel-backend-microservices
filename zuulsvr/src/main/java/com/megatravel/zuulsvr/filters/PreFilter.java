package com.megatravel.zuulsvr.filters;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreFilter extends ZuulFilter {

	private static final String APP_HEADER = "MT-Forward-to-App";
	private static final String ENDPOINT_HEADER = "MT-Forward-to-Endpoint";

	private static final String CONTENT_TYPE = "text/xml";
	
	private static final String SOAP_PORT = "soap-port";
	
	@Autowired
	private DiscoveryClient discoveryClient;

	private ProxyRequestHelper helper = new ProxyRequestHelper();

	@Override
	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		String serviceName = request.getHeader(APP_HEADER);
		String endpoint = request.getHeader(ENDPOINT_HEADER);
		if(serviceName != null && endpoint != null) {
			List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
			if(serviceInstances.size() > 0) {
				int serviceIndex = 0;
				ServiceInstance serviceInstance = serviceInstances.get(serviceIndex);
				String soapPort = serviceInstance.getMetadata().get(SOAP_PORT);
				@SuppressWarnings("unused")
				String serviceHost = serviceInstance.getHost();
				String serviceFullAddress = "http://localhost:" + soapPort + "/" + endpoint;
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

}
