package com.megatravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.model.hotel.PriceList;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Long> {
	
	List<PriceList> findAllByHotelPriceList_Id(Long hotelId);
	
}
