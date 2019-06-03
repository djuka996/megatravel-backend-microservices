package com.megatravel.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.megatravel.model.hotel.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
		
	@Query("SELECT r FROM Room r WHERE LOWER(r.roomsHotel.address.city) LIKE LOWER(CONCAT('%',?1,'%')) AND r.capacity >= ?4 AND "
			+ "r.id NOT IN "
			+ "(SELECT res.roomReservation.id FROM RoomReservation res WHERE "
			+ "((res.beginDate >= ?2 AND res.endDate <= ?3) OR "
			+ "(res.beginDate >= ?2 AND res.beginDate <= ?3) OR "
			+ "(res.endDate >= ?2 AND res.endDate <= ?3) OR "
			+ "(res.beginDate <= ?2 AND res.endDate >= ?3)) )")
	public Page<Room> findResult(String city, Date beginDate, Date endDate, int numberOfPeople, Pageable pageable);
	
//	@Query("SELECT r FROM Room r WHERE LOWER(r.roomsHotel.address.city) LIKE LOWER(CONCAT('%',?1,'%')) AND r.capacity >= ?4 AND "
//			+ "LOWER(r.accomodationType.name) LIKE LOWER(CONCAT('%',?5,'%')) AND r.roomsHotel.rating >= ?6 AND "
//			+ "r.roomsHotel.id in (SELECT heo.hotelExtraOption.id FROM HotelExtraOption heo WHERE LOWER(heo.extraOption.name) LIKE LOWER(CONCAT('%',?7,'%')) ) AND "
//			+ "r.id NOT IN "
//			+ "(SELECT res.roomReservation.id FROM RoomReservation res WHERE "
//			+ "((res.beginDate >= ?2 AND res.endDate <= ?3) OR "
//			+ "(res.beginDate >= ?2 AND res.beginDate <= ?3) OR "
//			+ "(res.endDate >= ?2 AND res.endDate <= ?3) OR "
//			+ "(res.beginDate <= ?2 AND res.endDate >= ?3)) )")
//	public Page<Room> findResultAdvance(String city, Date beginDate, Date endDate, int numberOfPeople, String accomodationtype,
//			double category, String additionalService, Pageable pageable);
	
	@Query("SELECT r FROM Room r WHERE r.capacity >= ?4 AND "
			+ "LOWER(r.accomodationType.name) LIKE LOWER(CONCAT('%',?5,'%')) AND r.roomsHotel.rating >= ?6 AND "
			+ "r.roomsHotel.id in (SELECT heo.hotelExtraOption.id FROM HotelExtraOption heo WHERE LOWER(heo.extraOption.name) LIKE LOWER(CONCAT('%',?1,'%')) ) AND "
			+ "r.id NOT IN "
			+ "(SELECT res.roomReservation.id FROM RoomReservation res WHERE "
			+ "((res.beginDate >= ?2 AND res.endDate <= ?3) OR "
			+ "(res.beginDate >= ?2 AND res.beginDate <= ?3) OR "
			+ "(res.endDate >= ?2 AND res.endDate <= ?3) OR "
			+ "(res.beginDate <= ?2 AND res.endDate >= ?3)) )")
	public Page<Room> findResultAdvance(String additionalService, Date beginDate, Date endDate, int numberOfPeople, String accomodationtype,
			double category,  Pageable pageable);
	
}