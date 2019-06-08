package com.megatravel.accommodationservice.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.accommodationservice.configurations.WebApplicationContextLocator;
import com.megatravel.accommodationservice.dtos.AddressDTO;
import com.megatravel.accommodationservice.interfaces.AddressServiceInterface;

@WebService(endpointInterface = "com.megatravel.accommodationservice.interfaces.AddressServiceInterface")
public class AddressServiceImpl implements AddressServiceInterface {

	public static final String ENDPOINT = "/services/address";
	
	public AddressServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	@WebMethod
	public AddressDTO getHotelsAddress(@WebParam(name = "hotel-id") Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public AddressDTO createAddress(AddressDTO address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public AddressDTO updateAddress(AddressDTO address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public boolean removeAddress(AddressDTO address) {
		// TODO Auto-generated method stub
		return false;
	}

}
