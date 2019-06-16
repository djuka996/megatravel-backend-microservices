package com.megatravel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.dtosoap.room_reservation.RoomReservationDTO;
import com.megatravel.webservices.ReservationServiceImpl;

@Service
public class ReservationService{

	@Autowired
	private ReservationServiceImpl reservationServiceImpl;

	public List<RoomReservationDTO> getAllReservations() {
		return reservationServiceImpl.getAllReservations();
	}


	public RoomReservationDTO getReservation(Long id) {
		return reservationServiceImpl.getReservation(id);
	}


	public List<RoomReservationDTO> getRoomReservations(Long roomId) {
		return reservationServiceImpl.getRoomReservations(roomId);
	}


	public List<RoomReservationDTO> getHotelReservations(Long hotelId) {
		return reservationServiceImpl.getHotelReservations(hotelId);
	}


	public RoomReservationDTO createReservation(RoomReservationDTO roomReservation, Long roomId,Long userId) {
		return reservationServiceImpl.createReservation(roomReservation, roomId,userId);
	}


	public RoomReservationDTO updateReservation(RoomReservationDTO roomReservation) {
		return reservationServiceImpl.updateReservation(roomReservation);
	}


	public boolean deleteReservation(Long id) {
		return reservationServiceImpl.deleteReservation(id);
	}

}
