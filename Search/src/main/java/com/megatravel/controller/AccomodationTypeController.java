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

import com.megatravel.dto.hotel.AccomodationTypeDTO;
import com.megatravel.model.hotel.AccomodationType;
import com.megatravel.service.AccomodationTypeService;

@RestController
@CrossOrigin
@RequestMapping(value = "/accomodations")
public class AccomodationTypeController {
	
	@Autowired
	private AccomodationTypeService accomodationTypeService;
	
	@RequestMapping( method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<AccomodationTypeDTO>> getAllAccomodations() {
		List<AccomodationTypeDTO> accomodations = convertToDTOAccomodations(accomodationTypeService.findAll());

		HttpHeaders headers = new HttpHeaders();
		long accomodationTotal = accomodations.size();
		headers.add("X-Total-Count", String.valueOf(accomodationTotal));
		
		return new ResponseEntity<List<AccomodationTypeDTO>>(accomodations, headers, HttpStatus.OK);
	}
	
	private List<AccomodationTypeDTO> convertToDTOAccomodations(List<AccomodationType> accomodations) {
		List<AccomodationTypeDTO> retVal = new ArrayList<>();
		accomodations.forEach(accomodation -> {
			retVal.add(new AccomodationTypeDTO(accomodation));
		});
		
		return retVal;
	}
}
