package com.megatravel.controllers;

import java.util.ArrayList;
import java.util.List;

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
import com.megatravel.dtosoap.hotel.HotelDTO;
import com.megatravel.model.hotel.Hotel;
import com.megatravel.services.HotelService;

@RestController
@CrossOrigin
@RequestMapping(value = "/hotels")
public class HotelController {

	@Autowired
	private HotelService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<HotelDTO>> getAllHotels(HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("getAllHotels", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<List<HotelDTO>>(convertToListDTO(service.getAllHotels(request)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<HotelDTO> getHotel(@PathVariable("id") Long id,  HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("getHotel", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<HotelDTO>(new HotelDTO(service.getHotel(id,request)), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotel,  HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("createHotel", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<HotelDTO>(new HotelDTO(service.createHotel(hotel,request)), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<HotelDTO> updateHotel(@RequestBody HotelDTO hotel,  HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("updateHotel", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<HotelDTO>(new HotelDTO(service.updateHotel(hotel,request)), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeHotel(@PathVariable("id") Long id,  HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("removeHotel", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Boolean>(service.removeHotel(id,request), HttpStatus.ACCEPTED);
	}
	
	private List<HotelDTO> convertToListDTO(List<Hotel> got) {
		List<HotelDTO> ret = new ArrayList<>();
		for (Hotel hotel : got) {
			ret.add(new HotelDTO(hotel));			
		}
		return ret;
	}
	
}
