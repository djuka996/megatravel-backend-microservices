package com.megatravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.model.hotel.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
