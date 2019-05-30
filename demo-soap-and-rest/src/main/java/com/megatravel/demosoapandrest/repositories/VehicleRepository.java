package com.megatravel.demosoapandrest.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.demosoapandrest.model.Vehicle;

@Repository
public interface VehicleRepository extends PagingAndSortingRepository<Vehicle, Long> {

}
