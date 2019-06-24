package com.megatravel.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.dtosoap.global_parameters.AddressDTO;
import com.megatravel.model.global_parameters.Address;
import com.megatravel.model.hotel.Hotel;
import com.megatravel.repositories.AddressRepository;
import com.megatravel.repositories.HotelRepository;

@Service
public class AddressService{

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private HotelRepository hotelRepository;
	
	public Address getHotelsAddress(Long id) {
		Optional<Hotel> found = hotelRepository.findById(id);
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested hotel does not exist.");
		if(found.get().getAddress() == null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested hotel address does not exist.");
		return found.get().getAddress();
	}
	 
	public Address createAddress(AddressDTO address) {
		if(address == null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent.");
		if(address.getStreetNumber() <=0 || address.getCity().length()<=1 || address.getStreet().length()<=1)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No valid data sent.");
		Address toSave = new Address(address);
		Address saved = addressRepository.save(toSave);
		return saved;
	}
	 
	public Address updateAddress(AddressDTO address) {
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
		got.setLastChangedTime(new Date());
		Address saved = addressRepository.save(got);
		return saved;
	}
	 
	public boolean removeAddress(Long id) {
		Optional<Address> found = addressRepository.findById(id);
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Address does not exist.");
		addressRepository.delete(found.get());
		return true;
	}

}
