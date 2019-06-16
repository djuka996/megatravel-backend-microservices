package com.megatravel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.dtosoap.global_parameters.AddressDTO;
import com.megatravel.webservices.AddressServiceImpl;

@Service
public class AddressService{

	@Autowired
	private AddressServiceImpl addressServiceImpl;

	public AddressDTO getHotelsAddress(Long id) {
		return addressServiceImpl.getHotelsAddress(id);
	}
	 
	public AddressDTO createAddress(AddressDTO address) {
		return addressServiceImpl.createAddress(address);
	}
	 
	public AddressDTO updateAddress(AddressDTO address) {
		return addressServiceImpl.updateAddress(address);
	}
	 
	public boolean removeAddress(Long id) {
		return addressServiceImpl.removeAddress(id);
	}

}
