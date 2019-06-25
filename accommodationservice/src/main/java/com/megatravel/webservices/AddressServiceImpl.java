package com.megatravel.webservices;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configuration.WebApplicationContextLocator;
import com.megatravel.dtosoap.global_parameters.AddressDTO;
import com.megatravel.interfaces.AddressServiceInterface;
import com.megatravel.services.AddressService;

@WebService(portName="AddressServicePort",
serviceName="AddressService",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.AddressServiceInterface")
@Component
public class AddressServiceImpl implements AddressServiceInterface {

	public static final String ENDPOINT = "/services/address";
	
	@Autowired
	private AddressService addressService;
	
	public AddressServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	
	@Override
	public AddressDTO getHotelsAddress(Long id) {
		return new AddressDTO(addressService.getHotelsAddress(id,null));
	}

	@Override
	public AddressDTO createAddress(AddressDTO address) {
		return new AddressDTO(addressService.createAddress(address,null));
	}

	@Override
	public AddressDTO updateAddress(AddressDTO address) {
		return new AddressDTO(addressService.updateAddress(address,null));
	}

	@Override
	public boolean removeAddress(Long id) {
		return addressService.removeAddress(id,null);
	}

}
