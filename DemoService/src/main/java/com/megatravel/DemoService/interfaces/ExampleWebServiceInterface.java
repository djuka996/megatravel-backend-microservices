package com.megatravel.DemoService.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService(name = "ExampleWebServiceInterface",
			targetNamespace = "http://megatravel.com/example")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public interface ExampleWebServiceInterface {

	@WebMethod(operationName = "displayData")
	public int displayData(@WebParam(name = "name") String name, @WebParam(name = "years") int years);
	
}
