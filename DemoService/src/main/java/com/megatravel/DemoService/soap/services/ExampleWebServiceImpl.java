package com.megatravel.DemoService.soap.services;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.megatravel.DemoService.soap.interfaces.ExampleWebService;

@WebService(endpointInterface = "com.megatravel.DemoService.soap.interfaces.ExampleWebService")
public class ExampleWebServiceImpl implements ExampleWebService {

	@Override
	@WebMethod
	public String displayData(String name, int years) {
		return "User " + name + " is " + years + " old.";
	}

}
