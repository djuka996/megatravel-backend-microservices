package com.megatravel.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.model.hotel.PriceList;

public interface PriceListRepository extends JpaRepository<PriceList, Long>{

	List<PriceList> findAllByLastChangedTimeBetween(Date start, Date end);

}
