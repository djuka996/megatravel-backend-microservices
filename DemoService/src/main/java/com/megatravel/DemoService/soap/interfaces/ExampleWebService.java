package com.megatravel.DemoService.soap.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ExampleWebService {

	@WebMethod
	public String displayData(@WebParam(name = "name") String name, @WebParam(name = "years") int years);
	
}
