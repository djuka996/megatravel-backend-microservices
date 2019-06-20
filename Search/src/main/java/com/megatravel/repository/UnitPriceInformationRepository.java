package com.megatravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.model.hotel.UnitPriceInformation;


@Repository
public interface UnitPriceInformationRepository extends JpaRepository<UnitPriceInformation, Long> {

}
