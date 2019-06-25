package com.megatravel.repositories;

import com.megatravel.model.global_parameters.CurrencyPrice;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyPriceRepository extends JpaRepository<CurrencyPrice, Long> {

	List<CurrencyPrice> findAllByLastChangedTimeBetween(Date start, Date end);

}
