package com.megatravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.model.hotel.ExtraOption;

public interface ExtraOptionRepository extends JpaRepository<ExtraOption,Long> {

	//List<Room> findAllBy
	
}
