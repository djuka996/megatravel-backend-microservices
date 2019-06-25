package com.megatravel.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.model.hotel.HotelExtraOption;

public interface HotelExtraOptionRepository extends JpaRepository<HotelExtraOption, Long> {

	List<HotelExtraOption> findAllByLastChangedTimeBetween(Date start, Date end);

}
