package com.megatravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.model.hotel.AccomodationType;

public interface AccomodationTypeRepository extends JpaRepository<AccomodationType, Long> {

}
