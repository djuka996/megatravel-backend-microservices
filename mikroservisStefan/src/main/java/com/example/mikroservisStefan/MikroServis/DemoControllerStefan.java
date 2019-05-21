package com.example.mikroservisStefan.MikroServis;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stefan")
public class DemoControllerStefan {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testEndpoint() {
		return "Stefan is pro. Stefan knows to copy paste code";
	}
	
	@RequestMapping(value = "/test-post", method = RequestMethod.POST)
	public String testPostEndpoint(@RequestBody String message) {
		return "You've sent: From Stefan" + message;
	}
	
}
