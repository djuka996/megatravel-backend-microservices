package com.megatravel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.model.hotel.Image;
import com.megatravel.repository.ImageRepository;

@Service
public class ImageService {
	
	@Autowired
	ImageRepository imageRepository;

	public List<Image> findAll() {
		return imageRepository.findAll();
	}
	
	public Image save(Image image) {
		return imageRepository.save(image);
	}
}
