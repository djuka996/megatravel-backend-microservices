package com.megatravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.model.hotel.ExtraOption;

@Repository
public interface ExtraOptionRepository extends JpaRepository<ExtraOption,Long> {
	
}
