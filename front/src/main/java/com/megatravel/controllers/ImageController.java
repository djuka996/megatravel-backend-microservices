package com.megatravel.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.megatravel.dtosoap.hotel.ImageDTO;
import com.megatravel.model.hotel.Image;
import com.megatravel.service.ImageService;
import com.megatravel.service.UploadFileService;

@RestController
@CrossOrigin
@RequestMapping(value = "/images")
public class ImageController {
	
	@Autowired
	UploadFileService uploadFileService;
	
	@Autowired
	private ImageService imageService;
	
	@PostMapping(value = "/uploadFile")
	public ResponseEntity<Void> uploadFileToPost(@RequestParam("file") MultipartFile file) {

		try {

			uploadFileService.init();
			uploadFileService.store(file);

			String attachmentName = file.getOriginalFilename();
			System.out.println(attachmentName);
			//String extension = attachmentName.substring(attachmentName.indexOf('.') + 1);
			
			Image image = new Image();
			image.setFilePath(attachmentName);
			imageService.save(image);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		
		return null;
	}
	
	//vide svi
	@RequestMapping( method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ImageDTO>> getAllImages() {
		List<ImageDTO> images = convertToDTOImages(imageService.findAll());

		HttpHeaders headers = new HttpHeaders();
		long imagesTotal = images.size();
		headers.add("X-Total-Count", String.valueOf(imagesTotal));
		
		return new ResponseEntity<List<ImageDTO>>(images, headers, HttpStatus.OK);
	}
	
	private List<ImageDTO> convertToDTOImages(List<Image> images) {
		List<ImageDTO> retVal = new ArrayList<>();
		images.forEach(image -> {
			retVal.add(new ImageDTO(image));
		});
		
		return retVal;
	}
}
