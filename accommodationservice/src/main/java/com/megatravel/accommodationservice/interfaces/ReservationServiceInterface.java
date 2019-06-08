package com.megatravel.accommodationservice.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.megatravel.accommodationservice.dtos.RoomReservationDTO;

@WebService
public interface ReservationServiceInterface {

	@WebMethod
	List<RoomReservationDTO> getAllReservations();
	
	@WebMethod
	RoomReservationDTO getReservation(Long id);
	
	@WebMethod
	List<RoomReservationDTO> getRoomReservations(Long roomId);
	
	@WebMethod
	List<RoomReservationDTO> getHotelReservations(Long hotelId);
	
	@WebMethod
	RoomReservationDTO createReservation(RoomReservationDTO roomReservation, Long roomId);
	
	@WebMethod
	RoomReservationDTO updateReservation(RoomReservationDTO roomReservation);
	
	@WebMethod
	boolean deleteReservation(Long id);
	
}
