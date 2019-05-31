package com.example.mikroservisStefan.MikroServis;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.mikroservisStefan.interfaces.DemoInterface;
import com.example.mikroservisStefan.interfaces.ToLoginInterface;
import com.example.mikroservisStefan.model.Student;
import com.example.mikroservisStefan.security.PermissionAccess;


@RestController
@RequestMapping(value = "/stefan")
public class DemoControllerStefan {
	
	@Autowired
	DemoInterface demoInterface;
	
	@Autowired
	ToLoginInterface toLoginInterface;
	
	@Autowired
	PermissionAccess permissionAccess;
	
	//@PermitAll
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
	
	@RequestMapping(value = "/objekat", method = RequestMethod.POST)
	public String testPostEndpointObject(@RequestBody Student student) {
		return "I got Student: " + student.toString();
	}
	

	@RequestMapping(value = "/studentKaDemo", method = RequestMethod.POST)
	public String testEndpointTudjiStudent(@RequestBody Student student) {
		return "pozvan iz stefan " + demoInterface.posaljiStudentaKaDemo(student);
	}
	
	@RequestMapping(value = "/kaLogin", method = RequestMethod.GET)
	public String testEndpointTudjiLogin(HttpServletRequest request) {
		return "pozvan iz stefan " + request.getLocalPort() + " " + toLoginInterface.metodaKaLogin();
	}
	
	@RequestMapping(value = "/test/pozivanjeTudjeg", method = RequestMethod.GET)
	public String testEndpointTudji(HttpServletRequest request) {
		System.out.println(request.getLocalPort());
		return request.getLocalPort() + demoInterface.metodaKaDemo();
	}
	
	//@PreAuthorize("@permissionAccess.canAccessString('Metoda')")
	@RequestMapping(value = "/test/jwt", method = RequestMethod.GET)
	public String probajZaJwt(HttpServletRequest request) {
		if(!permissionAccess.canAccessMethod("getAllUsers", request.getHeader("Authorization"))) {
			return "BACI exception";
		}
		
		//HotelDTO hotel = new HotelDTO();
		
		return request.getLocalPort() + "USPEO";
	}
	
	@RequestMapping(value = "/test/jwt/false", method = RequestMethod.GET)
	public String probajZaJwtFalse(HttpServletRequest request) {
		if(!permissionAccess.canAccessMethod("readAA", request.getHeader("Authorization"))) {
			return "BACI exception";
		}
		
		return request.getLocalPort() + "USPEO";
	}
}
