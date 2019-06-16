package com.megatravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.model.hotel.AccomodationType;

@Repository
public interface AccommodationTypeRepository extends JpaRepository<AccomodationType, Long> {

}
