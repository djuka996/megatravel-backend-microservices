package com.megatravel.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.megatravel.dtosoap.system_user_info.SystemUserLoginDTO;
import com.megatravel.dtosoap.system_user_info.SystemUserRegistrationDTO;

@WebService()
public interface LoginAndRegistrationService {

	@WebMethod(operationName = "testMethod")
	String testMethod();
	
	@WebMethod(operationName = "login")
	String login(@WebParam(name = "loginDTO") SystemUserLoginDTO loginDTO);
	
	@WebMethod(operationName = "signup")
	void signup(@WebParam(name = "registrationDTO") SystemUserRegistrationDTO registrationDTO);
	
}
