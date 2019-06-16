package com.megatravel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.megatravel.model.room_reservation.RoomReservation;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
	
	@Query("Select rr from RoomReservation rr where rr.roomReservation.id in "
		+ "(select ro from Room ro where ro.roomsHotel.id = ?1)")
	List<RoomReservation> findAllForHotel(Long hotelId);
	
	List<RoomReservation> findAllByRoomReservation_Id(Long RoomId);
}
