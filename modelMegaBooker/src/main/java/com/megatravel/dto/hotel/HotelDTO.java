//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.01 at 10:47:36 PM CEST 
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

import com.megatravel.dto.global_parameters.AddressDTO;
import com.megatravel.dtosoap.hotel.ImageDTO;
import com.megatravel.model.hotel.Hotel;
import com.megatravel.model.hotel.Image;


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
 *         &lt;element name="rating" type="{http://www.megatravel.com/global_parameters}Rating"/>
 *         &lt;element name="Address" type="{http://www.megatravel.com/global_parameters}AddressDTO"/>
 *         &lt;element ref="{http://www.megatravel.com/hotel}Extra_optionDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.megatravel.com/hotel}ImageDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.megatravel.com/hotel}Price_listDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.megatravel.com/hotel}RoomDTO" maxOccurs="unbounded" minOccurs="0"/>
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
    "rating",
    "address",
    "extraOptionDTO",
    "imageDTO",
    "priceListDTO"
})
@XmlRootElement(name = "HotelDTO")
public class HotelDTO {

    protected long id;
    protected double rating;
    @XmlElement(name = "Address", required = true)
    protected AddressDTO address;
    @XmlElement(name = "Extra_optionDTO")
    protected List<ExtraOptionDTO> extraOptionDTO;
    @XmlElement(name = "ImageDTO")
    protected List<ImageDTO> imageDTO;
    @XmlElement(name = "Price_listDTO")
    protected List<PriceListDTO> priceListDTO;
    // TODO : Anotacija mozda?
    protected Date lastChangedTime;
    
    public HotelDTO() {
    	
    }
    
    public HotelDTO(Hotel hotel) {
    	this.id = hotel.getId();
    	this.rating = hotel.getRating();
    	this.address = hotel.getAddress() != null ? new AddressDTO(hotel.getAddress()) : null;
		this.imageDTO = new ArrayList<>();
		for (Image image : hotel.getImage()) {
			this.imageDTO.add(new ImageDTO(image));
		}
		this.lastChangedTime = hotel.getLastChangedTime();
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
     * Gets the value of the rating property.
     * 
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets the value of the rating property.
     * 
     */
    public void setRating(double value) {
        this.rating = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link AddressDTO }
     *     
     */
    public AddressDTO getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressDTO }
     *     
     */
    public void setAddress(AddressDTO value) {
        this.address = value;
    }

    /**
     * Gets the value of the extraOptionDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extraOptionDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtraOptionDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtraOptionDTO }
     * 
     * 
     */
    public List<ExtraOptionDTO> getExtraOptionDTO() {
        if (extraOptionDTO == null) {
            extraOptionDTO = new ArrayList<ExtraOptionDTO>();
        }
        return this.extraOptionDTO;
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

    /**
     * Gets the value of the priceListDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the priceListDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPriceListDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PriceListDTO }
     * 
     * 
     */
    public List<PriceListDTO> getPriceListDTO() {
        if (priceListDTO == null) {
            priceListDTO = new ArrayList<PriceListDTO>();
        }
        return this.priceListDTO;
    }

	public Date getLastChangedTime() {
		return lastChangedTime;
	}

	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}

}
