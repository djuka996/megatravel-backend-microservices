package com.megatravel.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.megatravel.model.hotel.Image;
import com.megatravel.model.hotel.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

	List<Room> findAllByRoomsHotel_IdOrderByCurrentlyPriceAsc(Long id);
	
	List<Room> findAllByRoomsHotel_IdOrderByCurrentlyPriceDesc(Long id);
	
	List<Room> findAllByRoomsHotel_Id(Long id);
	
	@Query("SELECT AVG(r.rating) FROM UserReview r WHERE r.room.id = ?1 AND r.approved = true")
	double updateRating(Long id);

	List<Room> findAllByLastChangedTimeBetween(Date start, Date end);
	
	List<Image> findAllImagesByRoomId(Long RoomId);
	
}
