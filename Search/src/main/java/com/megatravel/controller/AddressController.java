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

import com.megatravel.dto.global_parameters.AddressDTO;
import com.megatravel.model.global_parameters.Address;
import com.megatravel.service.AddressService;

@RestController
@CrossOrigin
@RequestMapping(value = "/addresses")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@RequestMapping( method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<AddressDTO>> getAllImages() {
		List<AddressDTO> addresses = convertToDTOAddresses(addressService.findAll());

		HttpHeaders headers = new HttpHeaders();
		long addressesTotal = addresses.size();
		headers.add("X-Total-Count", String.valueOf(addressesTotal));
		
		return new ResponseEntity<List<AddressDTO>>(addresses, headers, HttpStatus.OK);
	}
	
	private List<AddressDTO> convertToDTOAddresses(List<Address> addresses) {
		List<AddressDTO> retVal = new ArrayList<>();
		addresses.forEach(address -> {
			retVal.add(new AddressDTO(address));
		});
		
		return retVal;
	}
}
