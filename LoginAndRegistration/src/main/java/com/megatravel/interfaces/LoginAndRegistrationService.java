package com.megatravel.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.megatravel.dto.system_user_info.SystemUserRegistrationDTO;
import com.megatravel.models.SystemUserLoginDTO;

@WebService
//@XmlSeeAlso({com.megatravel.dto.system_user_info.ObjectFactory.class, 
//	com.megatravel.dto.global_parameters.ObjectFactory.class, 
//	com.megatravel.dto.room_reservation.ObjectFactory.class,
//	com.megatravel.dto.hotel.ObjectFactory.class})
//@SOAPBinding( use = Use.LITERAL)
public interface LoginAndRegistrationService {

	@WebMethod(operationName = "testMethod")
	String testMethod();
	
	@WebMethod(operationName = "login")
	String login(@WebParam(name = "loginDTO") SystemUserLoginDTO loginDTO);
	
	@WebMethod(operationName = "signup")
	void signup(@WebParam(name = "registrationDTO") SystemUserRegistrationDTO registrationDTO);
	
}
