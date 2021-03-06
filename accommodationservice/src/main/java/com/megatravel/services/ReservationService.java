package com.megatravel.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.decodeJWT.DecodeJwtToken;
import com.megatravel.dtosoap.room_reservation.RoomReservationDTO;
import com.megatravel.model.hotel.Image;
import com.megatravel.model.hotel.Room;
import com.megatravel.model.room_reservation.RoomReservation;
import com.megatravel.model.system_user_info.User;
import com.megatravel.repositories.RoomRepository;
import com.megatravel.repositories.RoomReservationRepository;
import com.megatravel.repositories.UserRepository;

@Service
public class ReservationService{

	@Autowired
	private RoomReservationRepository reservationRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoomRepository roomRepository;
	
	
	
	public List<RoomReservation> getAllReservations(HttpServletRequest request) {
		List<RoomReservation> found = reservationRepository.findAll();
		if(found.size()==0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data.");
		return found;
	}


	public List<RoomReservation> getAllReservationsForUser(Long id,HttpServletRequest request){
		List<RoomReservation> found = reservationRepository.findAllByUsersReservation_Id(id);
		if(found.size()==0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data.");
		return found;
	}
	
	public RoomReservation getReservation(Long id,HttpServletRequest request) {
		if(id == null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Null requested content.");
		Optional<RoomReservation> found = reservationRepository.findById(id);
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No requested content.");
		return found.get();
	}


	public List<RoomReservation> getRoomReservations(Long roomId,HttpServletRequest request) {
		List<RoomReservation> found = reservationRepository.findAllByRoomReservation_Id(roomId);
		if(found.size()==0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No requested content.");
		return found;
	}


	public List<RoomReservation> getHotelReservations(Long hotelId,HttpServletRequest request) {
		List<RoomReservation> found = reservationRepository.findAllForHotel(hotelId);
		if(found.size()==0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No requested content.");
		return found;
	}
	
	public List<com.megatravel.dto.hotel.ImageDTO> getImagesForRoom(Long roomId) {
		List<com.megatravel.model.hotel.Image> found = roomRepository.findAllImagesByRoomId(roomId);
		if(found.size()==0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No requested content.");
		
		List<com.megatravel.dto.hotel.ImageDTO> imagesDTO = new ArrayList<com.megatravel.dto.hotel.ImageDTO>();
		
		for (Image image : found) {
			imagesDTO.add(new com.megatravel.dto.hotel.ImageDTO(image));
		}
		
		return imagesDTO;
	}


	public RoomReservation createReservation(RoomReservationDTO roomReservation, Long roomId,Long userId,HttpServletRequest request) {
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
		newRoom.setLastChangedTime(new Date());
		RoomReservation saved = reservationRepository.save(newRoom);
		return saved;
	}


	public RoomReservation updateReservation(RoomReservationDTO roomReservation,HttpServletRequest request) {
		if(roomReservation == null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent.");
		RoomReservation found = this.getReservation(roomReservation.getId(),request);
		RoomReservation got = found;
		got.setRealised(roomReservation.isRealised());
		RoomReservation saved = reservationRepository.save(got);
		return saved;
	}


	public boolean deleteReservation(Long id, HttpServletRequest request) {
		RoomReservation found = this.getReservation(id,request);
		reservationRepository.delete(found);
		return true;
	}

	public boolean cancelReservation(Long id,HttpServletRequest request) {
		RoomReservation found = this.getReservation(id,request);
		if(found.isRealised())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Not allowed to cancel.");
		if(!checkIfOkayToCancel(found))
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Not allowed to cancel.");
		if(!found.getUsersReservation().getEmail().equals(DecodeJwtToken.getUsername(request.getHeader("Authorization"))))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not allowed to cancel.");
		reservationRepository.delete(found);
		return true;
	}
	
	public static Boolean checkIfOkayToCancel(RoomReservation roomReservation) {
		if(roomReservation.isRealised())
			return false;
		//if(!roomReservation.getRoomReservation().isCancellationAllowed())
		//	return false;
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
