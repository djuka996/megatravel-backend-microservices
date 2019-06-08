package com.megatravel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.dto.hotel.ExtraOptionDTO;
import com.megatravel.model.hotel.ExtraOption;
import com.megatravel.service.ExtraOptionService;

@RestController
@CrossOrigin
@RequestMapping(value = "/extraoptions")
public class ExtraOptionController {
	@Autowired
	private ExtraOptionService extraOptionService;
	
	//vide svi
	@RequestMapping( method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ExtraOptionDTO>> getAllExtraOptions() {
		List<ExtraOptionDTO> options = convertToDTOOptions(extraOptionService.findAll());

		HttpHeaders headers = new HttpHeaders();
		long optionsTotal = options.size();
		headers.add("X-Total-Count", String.valueOf(optionsTotal));
		
		return new ResponseEntity<List<ExtraOptionDTO>>(options, headers, HttpStatus.OK);
	}
	
	private List<ExtraOptionDTO> convertToDTOOptions(List<ExtraOption> options) {
		List<ExtraOptionDTO> retVal = new ArrayList<>();
		options.forEach(option -> {
			retVal.add(new ExtraOptionDTO(option));
		});
		
		return retVal;
	}
}
