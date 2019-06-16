package com.megatravel.webservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.configurations.WebApplicationContextLocator;
import com.megatravel.dtosoap.hotel.HotelDTO;
import com.megatravel.interfaces.HotelServiceInterface;
import com.megatravel.model.global_parameters.Address;
import com.megatravel.model.hotel.Hotel;
import com.megatravel.repositories.AdressRepository;
import com.megatravel.repositories.ExtraOptionRepository;
import com.megatravel.repositories.HotelRepository;

@WebService(portName="ExtraOptionServicePort",
serviceName="ExtraOptionService",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.HotelServiceInterface")
public class HotelServiceImpl implements HotelServiceInterface {

	public static final String ENDPOINT = "/services/hotels";
	
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private AdressRepository adressRepository;
	@Autowired
	private ExtraOptionRepository extraOptionRepository;
	
	public HotelServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	public List<HotelDTO> getAllHotels() {
		List<Hotel> hotels = hotelRepository.findAll();
		
		if(hotels.size()>0) {
			List<HotelDTO> retVal = new ArrayList<>();
			for (Hotel user : hotels) {
				HotelDTO userDTO = new HotelDTO(user);
				retVal.add(userDTO);
			}	
			return retVal;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested content is empty.");
		}
	}

	@Override
	public HotelDTO getHotel(Long id) {
		Optional<Hotel> hotel = hotelRepository.findById(id);
		if(hotel.isPresent()) {
			return new HotelDTO(hotel.get());
		}
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested hotel with id " + id + " doesn't exist.");
		}
	}

	@Override
	public HotelDTO createHotel(HotelDTO hotel) {
		if(hotel == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No data sent");
		Hotel toSave = new Hotel(hotel);
		Optional<Address> adress = adressRepository.findById(hotel.getAddress().getId());
		if(!adress.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No adress data sent");
		toSave.setAddress(adress.get());
		Hotel saved = hotelRepository.save(toSave);
		HotelDTO returning = new HotelDTO(saved);
		return returning;
	}

	@Override
	public HotelDTO updateHotel(HotelDTO hotel) {
		if(hotel == null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent");
		
		Optional<Hotel> found = hotelRepository.findById(hotel.getId());
		if(found.isPresent())
		{
			Hotel got = found.get();
			//TODO SETOVATI
			// ExtraOptions <=> HotelExtraOptions?????		
			
			return new HotelDTO(got);
		}
		else 
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No existing hotel sent");
	}

	@Override
	public boolean removeHotel(Long id) {
		try {
			hotelRepository.deleteById(id);
			return true;
		}catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested removal of hotel with id " + id + " doesn't exist.");
		}
	}

}
