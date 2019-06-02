package com.megatravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.model.room_reservation.RoomReservation;

public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
	
//	@Query(value= "SELECT r.roomReservation FROM RoomReservation r "
//			+ "WHERE r.roomReservation.roomsHotel.address.city LIKE ?1 AND r.roomReservation.capacity >= ?4 AND"
//			+ 	"((r.beginDate >= ?2 AND r.endDate <= ?3) OR "
//			+	"(r.beginDate >= ?2 AND r.beginDate <= ?3) OR "
//			+	"(r.endDate >= ?2 AND r.endDate <= ?3) OR "
//			+	"(r.beginDate >= ?2 AND r.endDate<= ?3))")
//	public Page<Room> findResult(String city, String beginDate, String endDate, String numberOfPeople);
	

}
