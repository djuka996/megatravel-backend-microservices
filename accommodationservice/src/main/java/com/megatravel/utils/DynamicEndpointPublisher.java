package com.megatravel.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.ws.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megatravel.webservices.AccommodationTypeServiceImpl;
import com.megatravel.webservices.AddressServiceImpl;
import com.megatravel.webservices.ExtraOptionServiceImpl;
import com.megatravel.webservices.HotelServiceImpl;
import com.megatravel.webservices.ReservationServiceImpl;
import com.megatravel.webservices.RoomServiceImpl;
import com.netflix.appinfo.ApplicationInfoManager;

@Component
public class DynamicEndpointPublisher {

	private static final String SOAP_PORT = "soap-port";
	
	@Autowired
	private ApplicationInfoManager applicationInfoManager;
	
	@PostConstruct
	public void init() {
		Map<String, String> map = applicationInfoManager.getInfo().getMetadata();
		applicationInfoManager.refreshDataCenterInfoIfRequired();
		int port = this.getEmptyPort();
		map.put(SOAP_PORT, Integer.toString(port));
		publishEndpoint(port, AccommodationTypeServiceImpl.ENDPOINT, AccommodationTypeServiceImpl.class);
		publishEndpoint(port, AddressServiceImpl.ENDPOINT, AddressServiceImpl.class);
		publishEndpoint(port, ExtraOptionServiceImpl.ENDPOINT, ExtraOptionServiceImpl.class);
		publishEndpoint(port, HotelServiceImpl.ENDPOINT, HotelServiceImpl.class);
		publishEndpoint(port, ReservationServiceImpl.ENDPOINT, ReservationServiceImpl.class);
		publishEndpoint(port, RoomServiceImpl.ENDPOINT, RoomServiceImpl.class);
	}
	
	private int getEmptyPort() {
		int maximumAttempts = 50;
		while(maximumAttempts > 0) {
			try {
			ServerSocket serverSocket = new ServerSocket(0);
			int port = serverSocket.getLocalPort();
			serverSocket.close();
			return port;
			} catch(IOException e) {
				maximumAttempts--;
			}
		}
		return 0;
	}
	
	private void publishEndpoint(int port, String endpoint, Class<?> webService) {
		try {
			Endpoint.publish("http://0.0.0.0:" + port + endpoint, webService.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
}
