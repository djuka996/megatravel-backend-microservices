package com.megatravel.webservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.configurations.WebApplicationContextLocator;
import com.megatravel.dtosoap.room_reservation.RoomReservationDTO;
import com.megatravel.interfaces.ReservationServiceInterface;
import com.megatravel.model.room_reservation.RoomReservation;
import com.megatravel.repositories.RoomRepository;
import com.megatravel.repositories.RoomReservationRepository;

@WebService(portName="ReservationServicePort",
serviceName="ReservationService",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.ReservationServiceInterface")
public class ReservationServiceImpl implements ReservationServiceInterface {

	public static final String ENDPOINT = "/services/reservations";
	
	@Autowired
	private RoomReservationRepository reservationRepository;
	@Autowired
	private RoomRepository roomRepository;
	
	public ReservationServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	public List<RoomReservationDTO> getAllReservations() {
		List<RoomReservation> found = reservationRepository.findAll();
		if(found.size()==0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data.");
		List<RoomReservationDTO> returning = new ArrayList<>();
		for (RoomReservation roomReservation : found) {
			returning.add(new RoomReservationDTO(roomReservation));
		}
		return returning;
			
	}

	@Override
	public RoomReservationDTO getReservation(Long id) {
		Optional<RoomReservation> found = reservationRepository.findById(id);
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No requested content.");
		return new RoomReservationDTO(found.get());
	}

	@Override
	public List<RoomReservationDTO> getRoomReservations(Long roomId) {
		List<RoomReservation> found = reservationRepository.findAllByRoomReservation_Id(roomId);
		if(found.size()==0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No requested content.");
		List<RoomReservationDTO> returning = new ArrayList<>();
		for (RoomReservation roomReservation : found) {
			returning.add(new RoomReservationDTO(roomReservation));
		}
		return returning;
	}

	@Override
	public List<RoomReservationDTO> getHotelReservations(Long hotelId) {
		List<RoomReservation> found = reservationRepository.findAllForHotel(hotelId);
		if(found.size()==0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No requested content.");
		List<RoomReservationDTO> returning = new ArrayList<>();
		for (RoomReservation roomReservation : found) {
			returning.add(new RoomReservationDTO(roomReservation));
		}
		return returning;
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
		Optional<RoomReservation> found = reservationRepository.findById(id);
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No requested reservation.");
		reservationRepository.delete(found.get());
		return true;
	}

}
