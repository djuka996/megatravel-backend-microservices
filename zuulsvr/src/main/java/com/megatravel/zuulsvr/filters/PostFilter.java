package com.megatravel.zuulsvr.filters;

import org.springframework.beans.factory.annotation.Autowired;

import com.megatravel.zuulsvr.utils.StringUtilities;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PostFilter extends ZuulFilter {
	
	@Autowired
	private StringUtilities utilites;
	
	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return utilites.getFullURL(RequestContext.getCurrentContext().getRequest()).contains(StringUtilities.WSDL_SUFFIX);
	}

	@Override
	public Object run() {
		System.out.println("Usao post filter");
		RequestContext context = RequestContext.getCurrentContext();
		String body = context.getResponseBody();
		String url = utilites.getFullURL(context.getRequest());
		String location = utilites.createLocationFromURL(url);
		context.setResponseBody(utilites.replaceWebServicePort(body, location));
		System.out.println("Izasao post filter");
		return null;
	}

}
