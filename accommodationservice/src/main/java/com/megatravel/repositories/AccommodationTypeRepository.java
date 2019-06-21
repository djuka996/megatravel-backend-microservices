package com.megatravel.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.model.hotel.AccomodationType;

@Repository
public interface AccommodationTypeRepository extends JpaRepository<AccomodationType, Long> {

	List<AccomodationType> findAllByLastChangedTimeBetween(Date start, Date end);

}
