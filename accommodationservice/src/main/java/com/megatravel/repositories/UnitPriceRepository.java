package com.megatravel.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.model.hotel.UnitPriceInformation;

public interface UnitPriceRepository extends JpaRepository<UnitPriceInformation, Long> {

	List<UnitPriceInformation> findAllByLastChangedTimeBetween(Date start, Date end);

}
