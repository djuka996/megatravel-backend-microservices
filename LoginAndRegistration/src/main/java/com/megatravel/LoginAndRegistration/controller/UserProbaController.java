package com.megatravel.LoginAndRegistration.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "https://localhost:4200")
@RequestMapping(value = "/proba")
public class UserProbaController {


	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testEndpoint(HttpServletRequest request) {
		System.out.println(request.getLocalPort());
		return request.getLocalPort() + "Stefan is pro. Stefan knows how to copy paste code";
	}
}
