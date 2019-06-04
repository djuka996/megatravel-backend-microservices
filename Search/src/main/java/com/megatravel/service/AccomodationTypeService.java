package com.megatravel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.model.hotel.AccomodationType;
import com.megatravel.repository.AccomodationTypeRepository;

@Service
public class AccomodationTypeService {
	@Autowired
	AccomodationTypeRepository accomodationTypeRepository;
	
	public List<AccomodationType> findAll() {
		return accomodationTypeRepository.findAll();
	}
}
