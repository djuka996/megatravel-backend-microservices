package com.megatravel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.dtos.AddressDTO;
import com.megatravel.services.AddressService;

@RestController
@RequestMapping(value = "/hotels/{hotel-id}/address")
public class AddressController {

	@Autowired
	private AddressService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<AddressDTO> getHotelsAddress(@PathVariable("hotel-id") Long id) {
		return new ResponseEntity<AddressDTO>(new AddressDTO(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO address, @PathVariable("hotel-id") Long id) {
		return new ResponseEntity<AddressDTO>(new AddressDTO(), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO address, @PathVariable("hotel-id") Long id) {
		return new ResponseEntity<AddressDTO>(new AddressDTO(), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeAddress(@PathVariable("id") Long id) {
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
}
