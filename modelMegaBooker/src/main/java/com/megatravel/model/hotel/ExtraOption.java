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

import com.megatravel.dto.hotel.ExtraOptionDTO;

@Entity
public class ExtraOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    @OneToMany(mappedBy = "extraOption")
    protected Set<HotelExtraOption> hotelExtraOptions;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastChangedTime;
    
    public ExtraOption() {
    	
    }
    
    public ExtraOption(ExtraOptionDTO extraOptionDTO) {
		this.id = extraOptionDTO.getId();
		this.name = extraOptionDTO.getName();
	}
    
	public ExtraOption(Long id, String name) {
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
	public Set<HotelExtraOption> getHotelExtraOptions() {
		return hotelExtraOptions;
	}
	public void setHotelExtraOptions(Set<HotelExtraOption> hotelExtraOptions) {
		this.hotelExtraOptions = hotelExtraOptions;
	}

	public Date getLastChangedTime() {
		return lastChangedTime;
	}

	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}

}
