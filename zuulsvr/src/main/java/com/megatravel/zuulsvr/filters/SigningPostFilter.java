package com.megatravel.zuulsvr.filters;

import org.springframework.beans.factory.annotation.Autowired;

import com.megatravel.zuulsvr.interfaces.XmlServiceFeignClient;
import com.megatravel.zuulsvr.utils.StringUtilities;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class SigningPostFilter extends ZuulFilter {

	@Autowired
	private StringUtilities utilites;
	
	@Autowired
	private XmlServiceFeignClient service;
	
	@Override
	public boolean shouldFilter() {
		return !utilites.getFullURL(RequestContext.getCurrentContext().getRequest()).contains(StringUtilities.WSDL_SUFFIX)
				&& !utilites.getFullURL(RequestContext.getCurrentContext().getRequest()).contains(StringUtilities.XSD_SUFFIX)
				&& utilites.getFullURL(RequestContext.getCurrentContext().getRequest()).contains(StringUtilities.SOAP_PREFIX);
	}

	@Override
	public int filterOrder() {
		return 2;
	}
	
	@Override
	public String filterType() {
		return "post";
	}
	
	@Override
	public Object run() {
		System.out.println("Usao u SignignFilterPost");
		RequestContext context = RequestContext.getCurrentContext();
		String body = context.getResponseBody();
		String agentSerialNumber = context.getZuulRequestHeaders().get(StringUtilities.SERIAL_NUMBER_HEADER);
		String routeWithPrefix = context.getZuulRequestHeaders().get(StringUtilities.FORWARDED);
		String zuulRoute = "/" + routeWithPrefix.substring(routeWithPrefix.lastIndexOf('/') + 1) + "/**";
		String microserviceName = this.utilites.getMicroserviceNameFromRoute(zuulRoute);
		//context.setResponseBody(service.signAndEncode(body, agentSerialNumber, microserviceName).getBody());
		System.out.println("Izasao u SignignFilterPost");
		return null;
	}

}
