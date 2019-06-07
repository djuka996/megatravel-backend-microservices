package com.megatravel.accommodationservice.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.megatravel.accommodationservice.dtos.AddressDTO;

@WebService
public interface AddressServiceInterface {

	@WebMethod
	AddressDTO getHotelsAddress(Long id);
	
	@WebMethod
	AddressDTO createAddress(AddressDTO address);
	
	@WebMethod
	AddressDTO updateAddress(AddressDTO address);
	
	@WebMethod
	boolean removeAddress(AddressDTO address);
	
}
