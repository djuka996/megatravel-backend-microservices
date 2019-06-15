package com.megatravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.model.hotel.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
