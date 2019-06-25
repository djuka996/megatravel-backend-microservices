package com.megatravel.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.model.global_parameters.AmountType;

public interface AmountTypeRepository extends JpaRepository<AmountType, Long> {

	List<AmountType> findAllByLastChangedTimeBetween(Date start, Date end);

}
