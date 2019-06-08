package com.megatravel.dtos;

public class AddressDTO {

	private Long id;
	
	private String country;
	
	private String city;
	
	private String street;
	
	private int streetNumber;
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public int getStreetNumber() {
		return streetNumber;
	}
	
	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
