package com.megatravel.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.model.hotel.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	 Image findImageByFilePathEquals(String filepath);

	List<Image> findAllByLastChangedTimeBetween(Date start, Date end);
}
