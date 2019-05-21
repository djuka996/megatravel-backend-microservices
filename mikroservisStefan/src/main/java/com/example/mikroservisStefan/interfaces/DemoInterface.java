package com.example.mikroservisStefan.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("demoservice")
public interface DemoInterface {
	
	@RequestMapping(method = RequestMethod.GET, value = "/collection/test")
	public String metodaKaDemo();
}
