package com.megatravel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.dto.hotel.HotelDTO;
import com.megatravel.model.system_user_info.Privilege;


@RestController
@RequestMapping(value = "/next")
public class DemoControllerStefan {
	
//	@Autowired
//	PrivilegeRepository privilegeRepository;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testEndpoint(HttpServletRequest request) {
		HotelDTO hotel = new HotelDTO();
		Privilege pri = new Privilege();
		pri.setName("AJDEEEE");
		System.out.println(pri.getName());
		
		
//		System.out.println("PAUZA");
//		Privilege privilege = privilegeRepository.getOne(1L);
//		System.out.println(privilege.getName());
		
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
