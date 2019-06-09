package com.megatravel.zuulsvr.filters;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PostFilter extends ZuulFilter {
	
	private static final String WSDL_PORT = "soap:address location";
	private static final String WSDL_SUFFIX = "?wsdl";
	
	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 10;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletResponse response = context.getResponse();
		HttpServletRequest request = context.getRequest();
		String url = this.getFullURL(request);
		if(url.endsWith(WSDL_SUFFIX)) {
			String location = this.createLocationFromURL(url);
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
	
	private String createLocationFromURL(String url) {
		String location = url.substring(0, url.indexOf(WSDL_SUFFIX));
		return location;
	}
	
}
