package com.megatravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.model.hotel.ExtraOption;

@Repository
public interface ExtraOptionsRepository extends JpaRepository<ExtraOption, Long> {

}
