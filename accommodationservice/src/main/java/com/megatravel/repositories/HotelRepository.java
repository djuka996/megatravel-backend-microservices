package com.megatravel.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.model.hotel.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

	public List<Hotel> findAllByLastChangedTimeBetween(Date lastChangedTimeStart,
		      										   Date lastChangedTimeEnd);
	
}
