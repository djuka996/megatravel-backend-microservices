package com.megatravel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.model.hotel.ExtraOption;
import com.megatravel.repository.ExtraOptionsRepository;

@Service
public class ExtraOptionService {
	@Autowired
	ExtraOptionsRepository extraOptionsRepository;
	
	public List<ExtraOption> findAll() {
		return extraOptionsRepository.findAll();
	}
}
