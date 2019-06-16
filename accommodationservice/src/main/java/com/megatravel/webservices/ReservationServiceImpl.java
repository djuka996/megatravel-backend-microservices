package com.megatravel.webservices;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configurations.WebApplicationContextLocator;
import com.megatravel.dtosoap.room_reservation.RoomReservationDTO;
import com.megatravel.interfaces.ReservationServiceInterface;

@WebService(portName="ReservationServicePort",
serviceName="ReservationService",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.ReservationServiceInterface")
public class ReservationServiceImpl implements ReservationServiceInterface {

	public static final String ENDPOINT = "/services/reservations";
	
	public ReservationServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	public List<RoomReservationDTO> getAllReservations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomReservationDTO getReservation(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomReservationDTO> getRoomReservations(Long roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomReservationDTO> getHotelReservations(Long hotelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomReservationDTO createReservation(RoomReservationDTO roomReservation, Long roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomReservationDTO updateReservation(RoomReservationDTO roomReservation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteReservation(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
