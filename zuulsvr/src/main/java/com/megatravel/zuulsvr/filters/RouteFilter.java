package com.megatravel.zuulsvr.filters;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class RouteFilter extends ZuulFilter {

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
				String serviceHost = serviceInstance.getHost();
				String serviceFullAddress = "http://localhost:" + soapPort + "/" + endpoint;
				forwardToRoute(serviceFullAddress);
			}
		}
		return null;
	}

	private InputStream getRequestBody(HttpServletRequest request) {
		InputStream requestEntity = null;
		try {
			requestEntity = request.getInputStream();
		} catch (IOException ex) { }
		return requestEntity;
	}

	private void forwardToRoute(String targetUri) {
		// Preuzimanje podataka kako bi se kopirao zahtev 
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		MultiValueMap<String, String> headers = helper.buildZuulRequestHeaders(request);
		MultiValueMap<String, String> params = helper.buildZuulRequestQueryParams(request);
		InputStream requestEntity = this.getRequestBody(request);
		// Slanje zahteva
		this.helper.addIgnoredHeaders();
		HttpResponse response = null;
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			response = forward(httpClient,
					targetUri,
					request,
					headers,
					params,
					requestEntity);
			System.out.println(convertStreamToString(response.getEntity().getContent()));
			//setResponse(response);
			context.setResponseBody(convertStreamToString(response.getEntity().getContent()));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// TEMPORARY -----------------------------------------------------------------------------------------
	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
	
	private HttpResponse forward(HttpClient httpclient, String uri,
			HttpServletRequest request, MultiValueMap<String, String> headers,
			MultiValueMap<String, String> params, InputStream requestEntity)
					throws Exception {
		URL host = new URL( uri );
		HttpHost httpHost = getHttpHost(host);
		HttpRequest httpRequest;
		int contentLength = request.getContentLength();
		InputStreamEntity entity = new InputStreamEntity(requestEntity, contentLength, ContentType.create(CONTENT_TYPE, StandardCharsets.UTF_8));
		HttpPost httpPost = new HttpPost(uri);
		httpRequest = httpPost;
		httpPost.setEntity(entity);
		try {
			httpRequest.setHeaders(convertHeaders(headers));
			HttpResponse zuulResponse = forwardRequest(httpclient, httpHost, httpRequest);
			return zuulResponse;
		} finally { }
	}

	private HttpResponse forwardRequest(HttpClient httpclient, HttpHost httpHost,
			HttpRequest httpRequest) throws IOException {
		return httpclient.execute(httpHost, httpRequest);
	}

	private Header[] convertHeaders(MultiValueMap<String, String> headers) {
		List<Header> list = new ArrayList<>();
		for (String name : headers.keySet()) {
			for (String value : headers.get(name)) {
				list.add(new BasicHeader(name, value));
			}
		}
		return list.toArray(new BasicHeader[0]);
	}

	private HttpHost getHttpHost(URL host) {
		HttpHost httpHost = new HttpHost(host.getHost(), host.getPort(),
				host.getProtocol());
		return httpHost;
	}

	private void setResponse(HttpResponse response) throws IOException {
		this.helper.setResponse(response.getStatusLine().getStatusCode(),
				response.getEntity() == null ? null : response.getEntity().getContent(),
						revertHeaders(response.getAllHeaders()));
	}

	private MultiValueMap<String, String> revertHeaders(Header[] headers) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		for (Header header : headers) {
			String name = header.getName();
			if (!map.containsKey(name)) {
				map.put(name, new ArrayList<String>());
			}
			map.get(name).add(header.getValue());
		}
		return map;
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