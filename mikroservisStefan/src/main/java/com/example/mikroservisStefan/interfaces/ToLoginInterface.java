package com.example.mikroservisStefan.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("loginAndRegistration")
public interface ToLoginInterface {

	@RequestMapping(method = RequestMethod.GET, value = "/proba/test")
	public String metodaKaLogin();
}
