package com.megatravel.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configuration.WebApplicationContextLocator;
import com.megatravel.dtosoap.room_reservation.RoomReservationDTO;
import com.megatravel.interfaces.ReservationServiceInterface;
import com.megatravel.model.room_reservation.RoomReservation;
import com.megatravel.services.ReservationService;

@WebService(portName="ReservationServicePort",
serviceName="ReservationService",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.ReservationServiceInterface")
public class ReservationServiceImpl implements ReservationServiceInterface {

	public static final String ENDPOINT = "/services/reservations";
	

	@Autowired
	private ReservationService reservationService;
	
	public ReservationServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	public List<RoomReservationDTO> getAllReservationsForUser(Long userId) {
		return convertToListDTO(reservationService.getAllReservationsForUser(userId));
	}
	
	@Override
	public List<RoomReservationDTO> getAllReservations() {
		return convertToListDTO(reservationService.getAllReservations());
	}

	@Override
	public RoomReservationDTO getReservation(Long id) {
		return new RoomReservationDTO(reservationService.getReservation(id));
	}

	@Override
	public List<RoomReservationDTO> getRoomReservations(Long roomId) {
		return convertToListDTO(reservationService.getRoomReservations(roomId));
	}

	@Override
	public List<RoomReservationDTO> getHotelReservations(Long hotelId) {
		return convertToListDTO(reservationService.getHotelReservations(hotelId));
	}

	@Override
	public RoomReservationDTO createReservation(RoomReservationDTO roomReservation, Long roomId,Long userId) {
		return new RoomReservationDTO(reservationService.createReservation(roomReservation, roomId, userId));
	}

	@Override //TODO Proveriti da li samo "realisation" se menja?
	public RoomReservationDTO updateReservation(RoomReservationDTO roomReservation) {
		return new RoomReservationDTO(reservationService.updateReservation(roomReservation));
	}

	@Override
	public boolean deleteReservation(Long id) {
		return reservationService.deleteReservation(id);
	}

	@Override
	public boolean cancelReservation(Long id) {
		return reservationService.cancelReservation(id);
	}
	
	public List<RoomReservationDTO> convertToListDTO(List<RoomReservation> got) {
		List<RoomReservationDTO> retVal = new ArrayList<>();
		for (RoomReservation iter : got) {
			RoomReservationDTO newObject = new RoomReservationDTO(iter);
			retVal.add(newObject);
		}	
		return retVal;
	}

}
