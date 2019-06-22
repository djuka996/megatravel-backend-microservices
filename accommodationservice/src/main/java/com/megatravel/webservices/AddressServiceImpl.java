package com.megatravel.webservices;

import java.util.Optional;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.configuration.WebApplicationContextLocator;
import com.megatravel.dtosoap.global_parameters.AddressDTO;
import com.megatravel.interfaces.AddressServiceInterface;
import com.megatravel.model.global_parameters.Address;
import com.megatravel.model.hotel.Hotel;
import com.megatravel.repositories.AddressRepository;
import com.megatravel.repositories.HotelRepository;

@WebService(portName="AddressServicePort",
serviceName="AddressService",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.AddressServiceInterface")
@Service
public class AddressServiceImpl implements AddressServiceInterface {

	public static final String ENDPOINT = "/services/address";
	
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	public AddressServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	public AddressDTO getHotelsAddress(Long id) {
		Optional<Hotel> found = hotelRepository.findById(id);
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested hotel does not exist.");
		if(found.get().getAddress() == null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested hotel address does not exist.");

		AddressDTO returning = new AddressDTO(found.get().getAddress());
		return returning;
	}

	@Override
	public AddressDTO createAddress(AddressDTO address) {
		if(address == null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent.");
		if(address.getStreetNumber() <=0 || address.getCity().length()<=1 || address.getStreet().length()<=1)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No valid data sent.");
		Address toSave = new Address(address);
		Address saved = addressRepository.save(toSave);
		return new AddressDTO(saved);
	}

	@Override
	public AddressDTO updateAddress(AddressDTO address) {
		if(address == null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent.");
		if(address.getStreetNumber() <=0 || address.getCity().length()<=1 || address.getStreet().length()<=1)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No valid data sent.");
		Optional<Address> found = addressRepository.findById(address.getId());
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Address does not exist.");
		Address got = found.get();
		got.setCity(address.getCity());
		got.setCoordinateX(address.getCoordinateX());
		got.setCoordinateY(address.getCoordinateY());
		got.setCountry(address.getCountry());
		got.setStreet(address.getStreet());
		got.setStreetNumber(address.getStreetNumber());
		Address saved = addressRepository.save(got);
		return new AddressDTO(saved);
	}

	@Override
	public boolean removeAddress(Long id) {
		Optional<Address> found = addressRepository.findById(id);
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Address does not exist.");
		addressRepository.delete(found.get());
		return true;
	}

}
