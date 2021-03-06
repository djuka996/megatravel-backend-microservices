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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.megatravel.dto.hotel.PriceListDTO;

@Entity
public class PriceList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected Date beginDate;
	protected Date endDate;
	@OneToMany(mappedBy = "priceList")
	protected Set<UnitPriceInformation> unitPriceInformation;
	@ManyToOne()
	protected Hotel hotelPriceList;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastChangedTime;
	
	public PriceList() {

	}

	public PriceList(PriceListDTO priceListDTO) {
		this.id = priceListDTO.getId();
		this.beginDate = priceListDTO.getBeginDate();
		this.endDate = priceListDTO.getEndDate();
	}

	public PriceList(Long id, Date beginDate, Date endDate) {
		super();
		this.id = id;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<UnitPriceInformation> getUnitPriceInformation() {
		return unitPriceInformation;
	}

	public void setUnitPriceInformation(Set<UnitPriceInformation> unitPriceInformation) {
		this.unitPriceInformation = unitPriceInformation;
	}
	
	public Hotel getHotelPriceList() {
		return hotelPriceList;
	}

	public void setHotelPriceList(Hotel hotelPriceList) {
		this.hotelPriceList = hotelPriceList;
	}

	public Date getLastChangedTime() {
		return lastChangedTime;
	}

	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}
}
