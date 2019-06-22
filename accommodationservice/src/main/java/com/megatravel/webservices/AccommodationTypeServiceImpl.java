package com.megatravel.webservices;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.configuration.WebApplicationContextLocator;
import com.megatravel.dtosoap.hotel.AccomodationTypeDTO;
import com.megatravel.interfaces.AccommodationTypeServiceInterface;
import com.megatravel.model.hotel.AccomodationType;
import com.megatravel.repositories.AccommodationTypeRepository;

@WebService(portName="AccommodationTypeServicePort",
serviceName="AccommodationTypeService",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.AccommodationTypeServiceInterface")
public class AccommodationTypeServiceImpl implements AccommodationTypeServiceInterface {

	public static final String ENDPOINT = "/services/accommodations";
	
	@Autowired
	private AccommodationTypeRepository accommodationTypeRepository;
	
	public AccommodationTypeServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	

	@Override
	public List<AccomodationTypeDTO> getAll() {
		List<AccomodationType> accommodations = accommodationTypeRepository.findAll();
		if(accommodations.size() == 0)
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content in accomodations.");
		List<AccomodationTypeDTO> returning  = new ArrayList<>();
		for (AccomodationType accomodationType : accommodations) {
			returning.add(new AccomodationTypeDTO(accomodationType));
		}
		return returning;
	}
	
	@Override
	public AccomodationTypeDTO getRoomType(Long id) {
		Optional<AccomodationType> accommodation = accommodationTypeRepository.findById(id);
		if(accommodation.isPresent())
			return new AccomodationTypeDTO(accommodation.get());
		else 
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested accommodation does not exist.");
	}

	@Override
	public AccomodationTypeDTO createAccommodationType(AccomodationTypeDTO accommodationType) {		
		if(accommodationType==null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent.");
		if(accommodationType.getName().length()<=0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent.");
		AccomodationType toSave = new AccomodationType(accommodationType);
		toSave.setRooms(new HashSet<>());
		AccomodationType saved = accommodationTypeRepository.save(toSave);
		return new AccomodationTypeDTO(saved);
	}

	@Override
	public AccomodationTypeDTO updateAccommodationType(AccomodationTypeDTO accommodationType) {
		if(accommodationType==null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent.");
		if(accommodationType.getName().length()<=0 || accommodationType.getId() <= 0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent.");
		Optional<AccomodationType> accommodation = accommodationTypeRepository.findById(accommodationType.getId());
		AccomodationType found = accommodation.get();
		found.setId(accommodationType.getId());
		found.setName(accommodationType.getName());
		AccomodationType saved = accommodationTypeRepository.save(found);
		return new AccomodationTypeDTO(saved);
	}

	@Override
	public boolean removeAccommodationType(Long id) {
		Optional<AccomodationType> accommodation = accommodationTypeRepository.findById(id);
		if(accommodation.isPresent())
		{
			accommodationTypeRepository.delete(accommodation.get());
			return true;
		}
		else 
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested accommodation does not exist.");
	}
}
