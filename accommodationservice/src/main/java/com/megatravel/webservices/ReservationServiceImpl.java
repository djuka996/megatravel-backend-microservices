package com.megatravel.webservices;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.megatravel.model.hotel.Room;
import com.megatravel.model.room_reservation.RoomReservation;
import com.megatravel.model.system_user_info.User;
import com.megatravel.repositories.RoomRepository;
import com.megatravel.repositories.RoomReservationRepository;
import com.megatravel.repositories.UserRepository;

@WebService(portName="ReservationServicePort",
serviceName="ReservationService",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.ReservationServiceInterface")
public class ReservationServiceImpl implements ReservationServiceInterface {

	public static final String ENDPOINT = "/services/reservations";
	
	@Autowired
	private RoomReservationRepository reservationRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoomRepository roomRepository;
	
	public ReservationServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	public List<RoomReservationDTO> getAllReservationsForUser(Long userId) {
		List<RoomReservation> found = reservationRepository.findAllByUsersReservation_Id(userId);
		if(found.size()==0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data.");
		List<RoomReservationDTO> returning = new ArrayList<>();
		for (RoomReservation roomReservation : found) {
			RoomReservationDTO adding = new RoomReservationDTO(roomReservation);
			if(roomReservation.getRoomReservation().isCancellationAllowed())
				adding.setAllowedCancel(checkIfOkayToCancel(roomReservation));
			returning.add(adding);
		}
		return returning;
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
	public RoomReservationDTO createReservation(RoomReservationDTO roomReservation, Long roomId,Long userId) {
		if(roomReservation == null || roomId == null || userId == null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent.");
		Optional<User> foundUser = userRepository.findById(userId);
		if(!foundUser.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Not valid user in data sent.");
		Optional<Room> foundRoom = roomRepository.findById(roomId);
		if(!foundRoom.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Not valid room in data sent.");
		RoomReservation newRoom = new RoomReservation(roomReservation);		
		if(!checkValidDates(roomReservation.getBeginDate(),roomReservation.getEndDate()))
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Not valid dates in data sent.");
		List<RoomReservation> overlaping = reservationRepository.findOverlapsingReservations(roomId, roomReservation.getBeginDate(), roomReservation.getEndDate());
		if(overlaping.size()>0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Overlaping with other reservation for room.");			
		
		newRoom.setRoomReservation(foundRoom.get());
		newRoom.setRealised(false);
		newRoom.setUserReview(null);
		newRoom.setUsersReservation(foundUser.get());
		newRoom.setId(null);
		
		return null;
	}

	@Override //TODO Proveriti da li samo "realisation" se menja?
	public RoomReservationDTO updateReservation(RoomReservationDTO roomReservation) {
		if(roomReservation == null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent.");
		Optional<RoomReservation> found = reservationRepository.findById(roomReservation.getId());
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No requested data exists.");
		RoomReservation got = found.get();
		got.setRealised(roomReservation.isRealised());
		RoomReservation saved = reservationRepository.save(got);
		return new RoomReservationDTO(saved);
	}

	@Override
	public boolean deleteReservation(Long id) {
		Optional<RoomReservation> found = reservationRepository.findById(id);
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No requested reservation.");
		reservationRepository.delete(found.get());
		return true;
	}

	
	private Boolean checkIfOkayToCancel(RoomReservation roomReservation) {
		Calendar current = Calendar.getInstance();
		Calendar beginDate = Calendar.getInstance();
		current.setTime(new Date());
		beginDate.setTime(roomReservation.getBeginDate());
		current.add(Calendar.DATE, roomReservation.getRoomReservation().getCancellationDays());
		return current.before(beginDate);
	}
	
	/**
	 * 1)Check for beginDate before endDate => false
	 * 2)Check if beginDate == endDate => false
	 * @param beginDate
	 * @param endDate
	 * @return endDate>beginDate => true
	 */
	private Boolean checkValidDates(Date beginDate,Date endDate){
		if(beginDate == null || endDate ==null)
			return false;
		if(beginDate.after(endDate))
			return false;
	
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(beginDate);
		cal2.setTime(endDate);
		boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
		                  cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		if(sameDay)
			return false;
		return true;
	}
	
}
