package com.megatravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.model.hotel.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	 Image findImageByFilePathEquals(String filepath);
}
