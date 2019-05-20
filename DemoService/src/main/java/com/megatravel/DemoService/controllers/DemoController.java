package com.megatravel.DemoService.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/collection")
public class DemoController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testEndpoint() {
		return "Test endpoints works fine.";
	}
	
	@RequestMapping(value = "/test-post", method = RequestMethod.POST)
	public String testPostEndpoint(@RequestBody String message) {
		return "You've sent: " + message;
	}
	
}
