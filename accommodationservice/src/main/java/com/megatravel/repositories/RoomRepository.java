package com.megatravel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.model.hotel.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

	List<Room> findAllByRoomsHotel_IdOrderByCurrentlyPriceAsc(Long id);
	
	List<Room> findAllByRoomsHotel_IdOrderByCurrentlyPriceDesc(Long id);
	
	List<Room> findAllByRoomsHotel_Id(Long id);
	
}
