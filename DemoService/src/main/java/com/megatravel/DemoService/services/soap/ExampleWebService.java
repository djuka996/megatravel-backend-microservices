package com.megatravel.DemoService.services.soap;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.megatravel.DemoService.interfaces.ExampleWebServiceInterface;

@Stateless
@WebService(portName = "ExampleWebServicePort", 
			serviceName = "ExampleWebService", 
			targetNamespace = "http://megatravel.com/example", 
			endpointInterface = "com.megatravel.DemoService.services.soap.WebServiceExampleInterface", 
			wsdlLocation = "wsdl/ExampleWebService.wsdl",
			name = "ExampleWebServiceInterface")
public class ExampleWebService implements ExampleWebServiceInterface {

	@Override
	public int displayData(String name, int years) {
		System.out.println("User " + name + " is " + years + " old.");
		return years*10;
	}

}
