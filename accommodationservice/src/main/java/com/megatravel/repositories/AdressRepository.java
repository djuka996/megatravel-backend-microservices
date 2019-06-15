package com.megatravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.model.global_parameters.Address;

public interface AdressRepository extends JpaRepository<Address, Long> {

}
