package com.example.mikroservisStefan.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.mikroservisStefan.model.Student;

@FeignClient("demoservice")
public interface DemoInterface {
	
	@RequestMapping(method = RequestMethod.GET, value = "/collection/test")
	public String metodaKaDemo();
	
	@RequestMapping(method = RequestMethod.POST, value = "/collection/objekat")
	public String posaljiStudentaKaDemo(@RequestBody Student student);
}
