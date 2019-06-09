package com.megatravel.webservices;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configurations.WebApplicationContextLocator;
import com.megatravel.dtosoap.global_parameters.AddressDTO;
import com.megatravel.interfaces.AddressServiceInterface;

@WebService(portName="AddressServicePort",
serviceName="AddressServiceInterface",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.accommodationservice.interfaces.AddressServiceInterface")
public class AddressServiceImpl implements AddressServiceInterface {

	public static final String ENDPOINT = "/services/address";
	
	public AddressServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	public AddressDTO getHotelsAddress(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressDTO createAddress(AddressDTO address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressDTO updateAddress(AddressDTO address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAddress(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
