package com.megatravel.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.megatravel.dtosoap.room_reservation.RoomReservationDTO;


@WebService
public interface ReservationServiceInterface {

	@WebMethod
	List<RoomReservationDTO> getAllReservations();
	
	@WebMethod
	List<RoomReservationDTO> getAllReservationsForUser(Long userId);
	
	@WebMethod
	RoomReservationDTO getReservation(@XmlElement(name = "reservation-id", nillable = false, required = true) Long id);
	
	@WebMethod
	List<RoomReservationDTO> getRoomReservations(@XmlElement(name = "room-id", nillable = false, required = true) Long roomId);
	
	@WebMethod
	List<RoomReservationDTO> getHotelReservations(@XmlElement(name = "hotel-id", nillable = false, required = true) Long hotelId);
	
	@WebMethod
	RoomReservationDTO createReservation(@XmlElement(name = "reservation", nillable = false, required = true) RoomReservationDTO roomReservation, 
										 @XmlElement(name = "room-id", nillable = false, required = true) Long roomId,
										 @XmlElement(name = "user-id", nillable = false, required = true) Long userId);
	
	@WebMethod
	RoomReservationDTO updateReservation(@XmlElement(name = "reservation", nillable = false, required = true) RoomReservationDTO roomReservation);
	
	@WebMethod
	boolean deleteReservation(@XmlElement(name = "reservation-id", nillable = false, required = true) Long id);
	
}
