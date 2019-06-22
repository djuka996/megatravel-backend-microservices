package com.megatravel.zuulsvr.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megatravel.zuulsvr.interfaces.XmlServiceFeignClient;
import com.netflix.zuul.context.RequestContext;

@Component
public class SoapRequestCaller {

	private static final int TIMEOUT = 5000;
	
	private static final String POST = "POST";
	
	private static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
	
	@Autowired
	private StringUtilities utilites;
	
	@Autowired
	private XmlServiceFeignClient service;
	
	public ServiceResponse sendRequestTo(String fullUrl, String recipient, HttpServletRequest request) {
		URL url;
		HttpURLConnection connection = null;
		try {
			url = new URL(fullUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(request.getMethod());
			if(request.getMethod().equals(POST)) {
				String message = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
				connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
				connection.setRequestProperty("SOAPAction", utilites.returnSoapAction(fullUrl, message));
				connection.setDoOutput(true);
				//ResponseEntity<String> response = this.service.verifySignatureAndDecode(message, recipient, this.utilites.getWebServiceNameFromRoute(fullUrl));
				//message = response.getBody();
				//String serialNumber = response.getHeaders().getFirst("X-Serial-number");
				String serialNumber = "124324";
				RequestContext.getCurrentContext().addZuulRequestHeader(StringUtilities.SERIAL_NUMBER_HEADER, serialNumber);
				OutputStream outStream = connection.getOutputStream();
				OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream);
				outStreamWriter.write(message);
				outStreamWriter.flush();
				outStreamWriter.close();
				outStream.close();
			}
			connection.setConnectTimeout(TIMEOUT);
			connection.setReadTimeout(TIMEOUT);
			int status = connection.getResponseCode();
			StringBuffer content = new StringBuffer();
			if(status == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null) { content.append(inputLine); }
				in.close();
			}
			connection.disconnect();
			return new ServiceResponse(content.toString(), status);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return new ServiceResponse(INTERNAL_SERVER_ERROR, 500);
		} catch (IOException e) {
			e.printStackTrace();
			return new ServiceResponse(INTERNAL_SERVER_ERROR, 500);
		}
	}

}
