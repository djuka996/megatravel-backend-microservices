package com.megatravel.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.megatravel.model.global_parameters.Address;
import com.megatravel.repository.AdressRepository;

public class AdressService {
	@Autowired
	AdressRepository adressRepository;
	
	public Address findByCity(String city) {
		return adressRepository.findByCity(city);
	}
	
}
