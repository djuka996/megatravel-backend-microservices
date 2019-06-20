package com.megatravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.model.global_parameters.CurrencyPrice;

@Repository
public interface CurrencyPriceRepository extends JpaRepository<CurrencyPrice, Long> {

}
