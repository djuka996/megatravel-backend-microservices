//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.03 at 09:56:01 PM CEST 
//


package com.megatravel.dto.hotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.megatravel.dtosoap.hotel.ImageDTO;
import com.megatravel.model.hotel.Image;
import com.megatravel.model.hotel.Room;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.megatravel.com/global_parameters}Id"/>
 *         &lt;element name="description" type="{http://www.megatravel.com/global_parameters}Description"/>
 *         &lt;element name="cancellationDays">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *               &lt;maxInclusive value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cancellationAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="capacity">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *               &lt;maxInclusive value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="numberOfBeds">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *               &lt;maxInclusive value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="currentlyPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element ref="{http://www.megatravel.com/hotel}ImageDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "description",
    "cancellationDays",
    "cancellationAllowed",
    "capacity",
    "numberOfBeds",
    "currentlyPrice",
    "hotelDTO",
    "accomodationTypeDTO",
    "imageDTO",
    "lastChangedTime"
})
@XmlRootElement(name = "RoomDTO")
public class RoomDTO {

    protected long id;
    @XmlElement(required = true)
    protected String description;
    protected int cancellationDays;
    protected boolean cancellationAllowed;
    protected int capacity;
    protected int numberOfBeds;
    protected double currentlyPrice;
    @XmlElement(name = "HotelDTO", required = true)
    protected HotelDTO hotelDTO;
    @XmlElement(name = "Accomodation_typeDTO", required = true)
    protected AccomodationTypeDTO accomodationTypeDTO;
    @XmlElement(name = "ImageDTO")
    protected List<ImageDTO> imageDTO;
    // TODO : Anotacija mozda?
    @XmlElement(name = "LastChangedTime")
    protected Date lastChangedTime;
    
    public RoomDTO(Room room) {
		this.id = room.getId();
		this.description = room.getDescription();
		this.cancellationDays = room.getCancellationDays();
		this.cancellationAllowed = room.isCancellationAllowed();
		this.capacity = room.getCapacity();
		this.numberOfBeds = room.getNumberOfBeds();
		this.currentlyPrice = room.getCurrentlyPrice();
		this.hotelDTO = room.getRoomsHotel() != null ? new HotelDTO(room.getRoomsHotel()) : null;
		this.accomodationTypeDTO = room.getAccomodationType() != null ? new AccomodationTypeDTO(room.getAccomodationType()) : null;
		this.imageDTO = new ArrayList<>();
		for (Image image : room.getImages()) {
			this.imageDTO.add(new ImageDTO(image));
		}
		this.lastChangedTime = room.getLastChangedTime();
    }
    
    public RoomDTO() {
    	
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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the cancellationDays property.
     * 
     */
    public int getCancellationDays() {
        return cancellationDays;
    }

    /**
     * Sets the value of the cancellationDays property.
     * 
     */
    public void setCancellationDays(int value) {
        this.cancellationDays = value;
    }

    /**
     * Gets the value of the cancellationAllowed property.
     * 
     */
    public boolean isCancellationAllowed() {
        return cancellationAllowed;
    }

    /**
     * Sets the value of the cancellationAllowed property.
     * 
     */
    public void setCancellationAllowed(boolean value) {
        this.cancellationAllowed = value;
    }

    /**
     * Gets the value of the capacity property.
     * 
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the value of the capacity property.
     * 
     */
    public void setCapacity(int value) {
        this.capacity = value;
    }

    /**
     * Gets the value of the numberOfBeds property.
     * 
     */
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    /**
     * Sets the value of the numberOfBeds property.
     * 
     */
    public void setNumberOfBeds(int value) {
        this.numberOfBeds = value;
    }

    /**
     * Gets the value of the currentlyPrice property.
     * 
     */
    public double getCurrentlyPrice() {
        return currentlyPrice;
    }

    /**
     * Sets the value of the currentlyPrice property.
     * 
     */
    public void setCurrentlyPrice(double value) {
        this.currentlyPrice = value;
    }
    
    public HotelDTO getHotelDTO() {
        return hotelDTO;
    }

    /**
     * Sets the value of the hotelDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelDTO }
     *     
     */
    public void setHotelDTO(HotelDTO value) {
        this.hotelDTO = value;
    }
    
    /**
     * Gets the value of the accomodationTypeDTO property.
     * 
     * @return
     *     possible object is
     *     {@link AccomodationTypeDTO }
     *     
     */
    public AccomodationTypeDTO getAccomodationTypeDTO() {
        return accomodationTypeDTO;
    }

    /**
     * Sets the value of the accomodationTypeDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccomodationTypeDTO }
     *     
     */
    public void setAccomodationTypeDTO(AccomodationTypeDTO value) {
        this.accomodationTypeDTO = value;
    }
    /**
     * Gets the value of the imageDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the imageDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImageDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImageDTO }
     * 
     * 
     */
    public List<ImageDTO> getImageDTO() {
        if (imageDTO == null) {
            imageDTO = new ArrayList<ImageDTO>();
        }
        return this.imageDTO;
    }

	public Date getLastChangedTime() {
		return lastChangedTime;
	}

	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}


}
