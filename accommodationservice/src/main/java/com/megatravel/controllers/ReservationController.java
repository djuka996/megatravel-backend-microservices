package com.megatravel.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.dtosoap.room_reservation.RoomReservationDTO;
import com.megatravel.services.ReservationService;

@RestController
public class ReservationController {

	@Autowired
	private ReservationService service;
	
	@RequestMapping(value = "/reservations", method = RequestMethod.GET)
	public ResponseEntity<List<RoomReservationDTO>> getAllReservations() {
		return new ResponseEntity<List<RoomReservationDTO>>(new ArrayList<RoomReservationDTO>(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservations/{id}", method = RequestMethod.GET)
	public ResponseEntity<RoomReservationDTO> getReservation(@PathVariable("id") Long id) {
		return new ResponseEntity<RoomReservationDTO>(new RoomReservationDTO(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/hotels/{hotel-id}/rooms/{room-id}/reservations", method = RequestMethod.GET)
	public ResponseEntity<List<RoomReservationDTO>> getRoomReservations(@PathVariable("room-id") Long id) {
		return new ResponseEntity<List<RoomReservationDTO>>(new ArrayList<RoomReservationDTO>(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/hotels/{hotel-id}/reservations", method = RequestMethod.GET)
	public ResponseEntity<List<RoomReservationDTO>> getHotelReservations(@PathVariable("hotel-id") Long id) {
		return new ResponseEntity<List<RoomReservationDTO>>(new ArrayList<RoomReservationDTO>(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/hotels/{hotel-id}/rooms/{room-id}/reservations", method = RequestMethod.POST)
	public ResponseEntity<RoomReservationDTO> createReservation(@RequestBody RoomReservationDTO reservation, @PathVariable("room-id") Long id) {
		return new ResponseEntity<RoomReservationDTO>(new RoomReservationDTO(), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/reservations", method = RequestMethod.PUT)
	public ResponseEntity<RoomReservationDTO> updateReservation(@RequestBody RoomReservationDTO reservation) {
		return new ResponseEntity<RoomReservationDTO>(new RoomReservationDTO(), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/reservations/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeReservation(@PathVariable("id") Long id) {
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
}
