//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.21 at 06:51:34 PM CEST 
//


package com.megatravel.model.hotel;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.megatravel.dto.hotel.AccomodationTypeDTO;

@Entity
public class AccomodationType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    @OneToMany(mappedBy = "accomodationType")
    protected Set<Room> rooms;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastChangedTime;
    

	public AccomodationType() {
	}
    
    public AccomodationType(AccomodationTypeDTO accomodationTypeDTO) {
		this.id = accomodationTypeDTO.getId();
		this.name = accomodationTypeDTO.getName();
		this.lastChangedTime = new Date();
	}
    
    public AccomodationType(com.megatravel.dtosoap.hotel.AccomodationTypeDTO accomodationTypeDTO) {
		this.id = accomodationTypeDTO.getId();
		this.name = accomodationTypeDTO.getName();
	}
    
	public AccomodationType(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public Date getLastChangedTime() {
		return lastChangedTime;
	}

	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}
}
