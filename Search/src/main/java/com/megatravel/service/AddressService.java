package com.megatravel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.model.global_parameters.Address;
import com.megatravel.repository.AdressRepository;

@Service
public class AddressService {
	@Autowired
	AdressRepository adressRepository;
	
	public List<Address> findByCity(String city) {
		return adressRepository.findByCity(city);
	}
	
	public List<Address> findAll() {
		return adressRepository.findAll();
	}
	
}
