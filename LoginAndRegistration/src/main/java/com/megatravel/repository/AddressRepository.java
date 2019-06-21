package com.megatravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.model.global_parameters.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
