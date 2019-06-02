package com.megatravel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.megatravel.model.hotel.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
		
	@Query("SELECT r FROM Room r WHERE r.roomsHotel.address.city LIKE ?1 AND r.capacity >= ?4 AND "
			+ "r.id NOT IN "
			+ "(SELECT res.roomReservation.id FROM RoomReservation res WHERE "
			+ "((res.beginDate >= ?2 AND res.endDate <= ?3) OR "
			+ "(res.beginDate >= ?2 AND res.beginDate <= ?3) OR "
			+ "(res.endDate >= ?2 AND res.endDate <= ?3) OR "
			+ "(res.beginDate <= ?2 AND res.endDate >= ?3)) )")
	public Page<Room> findResult(String city, String beginDate, String endDate, int numberOfPeople, Pageable pageable);
	
	@Query("SELECT r FROM Room r WHERE r.roomsHotel.address.city LIKE ?1 AND r.capacity >= ?4 AND "
			+ "r.accomodationType.name LIKE ?5 AND r.roomsHotel.rating >= ?6 AND "
			+ "r.roomsHotel.id in (SELECT heo.id FROM HotelExtraOption heo WHERE heo.extraOption.name LIKE ?7) AND "
			+ "r.id NOT IN "
			+ "(SELECT res.roomReservation.id FROM RoomReservation res WHERE "
			+ "((res.beginDate >= ?2 AND res.endDate <= ?3) OR "
			+ "(res.beginDate >= ?2 AND res.beginDate <= ?3) OR "
			+ "(res.endDate >= ?2 AND res.endDate <= ?3) OR "
			+ "(res.beginDate <= ?2 AND res.endDate >= ?3)) )")
	public Page<Room> findResultAdvance(String city, String beginDate, String endDate, int numberOfPeople, String accomodationtype,
			double category, String additionalService, Pageable pageable);
	
}