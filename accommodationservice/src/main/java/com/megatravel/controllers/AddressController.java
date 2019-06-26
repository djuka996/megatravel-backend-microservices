package com.megatravel.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.decodeJWT.DecodeJwtToken;
import com.megatravel.dtosoap.global_parameters.AddressDTO;
import com.megatravel.services.AddressService;

@RestController
@CrossOrigin
@RequestMapping(value = "/hotels/{hotel-id}/address")
public class AddressController {

	@Autowired
	private AddressService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<AddressDTO> getHotelsAddress(@PathVariable("hotel-id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("getHotelsAddress", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<AddressDTO>(new AddressDTO(service.getHotelsAddress(id,request)), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO address, @PathVariable("hotel-id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("createAddress", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<AddressDTO>(new AddressDTO(service.createAddress(address,request)), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO address, @PathVariable("hotel-id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("updateAddress", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<AddressDTO>(new AddressDTO(service.updateAddress(address,request)), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeAddress(@PathVariable("id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("removeAddress", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Boolean>(service.removeAddress(id,request),HttpStatus.ACCEPTED);
	}
	
}
