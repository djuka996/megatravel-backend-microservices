package com.megatravel.DemoService.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.DemoService.model.Student;

@RestController
@RequestMapping(value = "/collection")
public class DemoController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testEndpoint(HttpServletRequest request) {
		System.out.println(request.getLocalPort());
		return request.getLocalPort() + "Test endpoints works fine.";
	}
	
	@RequestMapping(value = "/test-post", method = RequestMethod.POST)
	public String testPostEndpoint(@RequestBody String message) {
		return "You've sent: " + message;
	}
	
	@RequestMapping(value = "/objekat", method = RequestMethod.POST)
	public String testPostEndpointObject(@RequestBody Student student) {
		return "I got Student DEMO: " + student.toString();
	}
	
}
