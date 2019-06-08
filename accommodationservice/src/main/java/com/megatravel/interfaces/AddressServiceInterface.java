package com.megatravel.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.megatravel.dtos.AddressDTO;

@WebService
public interface AddressServiceInterface {

	@WebMethod
	AddressDTO getHotelsAddress(@XmlElement(name = "hotel-id", nillable = false, required = true) Long id);
	
	@WebMethod
	AddressDTO createAddress(@XmlElement(name = "new-address", nillable = false, required = true) AddressDTO address);
	
	@WebMethod
	AddressDTO updateAddress(@XmlElement(name = "new-address", nillable = false, required = true) AddressDTO address);
	
	@WebMethod
	boolean removeAddress(@XmlElement(name = "address-id", nillable = false, required = true) Long id);
	
}
