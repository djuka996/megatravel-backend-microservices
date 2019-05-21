package com.example.mikroservisStefan.MikroServis;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.mikroservisStefan.interfaces.DemoInterface;

@RestController
@RequestMapping(value = "/stefan")
public class DemoControllerStefan {
	
	@Autowired
	DemoInterface demoInterface;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testEndpoint(HttpServletRequest request) {
		System.out.println(request.getLocalPort());
		return "Stefan is pro. Stefan knows how to copy paste code";
	}
	
	@RequestMapping(value = "/test-post", method = RequestMethod.POST)
	public String testPostEndpoint(@RequestBody String message, HttpServletRequest request) {
		System.out.println(request.getLocalPort());
		return "You've sent: From Stefan" + message;
	}
	
	@RequestMapping(value = "/test/pozivanjeTudjeg", method = RequestMethod.GET)
	public String testEndpointTudji(HttpServletRequest request) {
		System.out.println(request.getLocalPort());
		return demoInterface.metodaKaDemo();
	}
	
}
