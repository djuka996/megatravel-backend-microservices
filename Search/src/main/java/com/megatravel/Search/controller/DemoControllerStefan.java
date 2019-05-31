package com.megatravel.Search.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/stefan")
public class DemoControllerStefan {
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testEndpoint(HttpServletRequest request) {
		System.out.println(request.getLocalPort());
		return request.getLocalPort() + "Stefan is pro. Stefan knows how to copy paste code";
	}
	
	//@PermitAll
	@RequestMapping(value = "/test-post", method = RequestMethod.POST)
	public String testPostEndpoint(@RequestBody String message, HttpServletRequest request) {
		System.out.println(request.getLocalPort());
		return "You've sent: From Stefan" + message;
	}
	
}
