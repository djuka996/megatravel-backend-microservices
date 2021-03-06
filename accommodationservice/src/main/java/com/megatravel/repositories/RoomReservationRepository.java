package com.megatravel.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.megatravel.model.room_reservation.RoomReservation;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
	
	List<RoomReservation> findAllByUsersReservation_Id(Long userId);
	
	@Query("Select rr from RoomReservation rr where rr.roomReservation.id in "
		+ "(select ro from Room ro where ro.roomsHotel.id = ?1)")
	List<RoomReservation> findAllForHotel(Long hotelId);
	
	List<RoomReservation> findAllByRoomReservation_Id(Long RoomId);
	
	@Query("Select r from RoomReservation r where r.roomReservation.id = ?1 AND r.id in "
			+ "(Select rr.id from RoomReservation rr where rr.roomReservation.id = r.roomReservation.id AND "
			+ "((rr.beginDate >= ?2 AND rr.endDate <= ?3) OR (rr.beginDate >= ?2 AND rr.beginDate <= ?3) OR (rr.endDate >= ?2 AND rr.endDate <= ?3) ))")
	List<RoomReservation> findOverlapsingReservations(Long roomId,Date beginDate,Date endDate);

	List<RoomReservation> findAllByLastChangedTimeBetween(Date start, Date end);
}
