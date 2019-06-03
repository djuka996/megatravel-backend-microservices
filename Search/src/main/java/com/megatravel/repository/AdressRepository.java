package com.megatravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.megatravel.model.global_parameters.Address;

@Repository
public interface AdressRepository extends JpaRepository<Address, Long> {
	
	@Query("SELECT a FROM Address a WHERE LOWER(a.city) LIKE LOWER(CONCAT('%',?1,'%'))")
	public List<Address> findByCity(String city);
	
}