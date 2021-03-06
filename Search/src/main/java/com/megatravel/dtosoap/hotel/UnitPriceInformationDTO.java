//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.21 at 06:51:34 PM CEST 
//


package com.megatravel.dtosoap.hotel;

import java.util.Date;

import com.megatravel.dtosoap.global_parameters.CurrencyPriceDTO;
import com.megatravel.model.hotel.UnitPriceInformation;

public class UnitPriceInformationDTO {

    protected long id;
    protected CurrencyPriceDTO price;
    protected RoomDTO roomDTO;
    protected Date lastChangedTime;
    protected PriceListDTO priceListDTO;
    
    public UnitPriceInformationDTO() { }
    
    public UnitPriceInformationDTO(UnitPriceInformation unitPriceInformation) {
    	this.id = unitPriceInformation.getId();
    	this.price = (unitPriceInformation.getPrice() == null) ? null : new CurrencyPriceDTO(unitPriceInformation.getPrice());
    	this.roomDTO = (unitPriceInformation.getRoom() == null) ? null : new RoomDTO(unitPriceInformation.getRoom());
    	this.lastChangedTime = unitPriceInformation.getLastChangedTime();
    	this.priceListDTO = (unitPriceInformation.getPriceList() == null) ? null : new PriceListDTO(unitPriceInformation.getPriceList());
    }
    
    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link CurrencyPriceDTO }
     *     
     */
    public CurrencyPriceDTO getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyPriceDTO }
     *     
     */
    public void setPrice(CurrencyPriceDTO value) {
        this.price = value;
    }

	public Date getLastChangedTime() {
		return lastChangedTime;
	}

	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}

	public RoomDTO getRoomDTO() {
		return roomDTO;
	}

	public void setRoomDTO(RoomDTO roomDTO) {
		this.roomDTO = roomDTO;
	}

	public PriceListDTO getPriceListDTO() {
		return priceListDTO;
	}

	public void setPriceListDTO(PriceListDTO priceListDTO) {
		this.priceListDTO = priceListDTO;
	}

}
