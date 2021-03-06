//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.21 at 06:51:34 PM CEST 
//


package com.megatravel.dto.room_reservation;

import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.megatravel.dto.hotel.Adapter1;
import com.megatravel.dto.hotel.RoomDTO;


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
 *         &lt;element name="Id" type="{http://www.megatravel.com/global_parameters}Id"/>
 *         &lt;element name="Begin_date" type="{http://www.megatravel.com/global_parameters}Date"/>
 *         &lt;element name="End_date" type="{http://www.megatravel.com/global_parameters}Date"/>
 *         &lt;element name="Realised" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{http://www.megatravel.com/hotel}RoomDTO" minOccurs="0"/>
 *         &lt;element name="Price" type="{http://www.megatravel.com/global_parameters}Price"/>
 *         &lt;element name="AllowedCancel" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "beginDate",
    "endDate",
    "realised",
    "roomDTO",
    "price"
})
@XmlRootElement(name = "Room_reservationDTO")
public class RoomReservationDTO {

    @XmlElement(name = "Id")
    protected long id;
    @XmlElement(name = "Begin_date", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    protected Date beginDate;
    @XmlElement(name = "End_date", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    protected Date endDate;
    @XmlElement(name = "Realised", defaultValue = "0")
    protected boolean realised;
    @XmlElement(name = "RoomDTO", namespace = "http://www.megatravel.com/hotel")
    protected RoomDTO roomDTO;
    @XmlElement(name = "Price", required = true)
    protected BigDecimal price;
    @XmlElement(name = "AllowedCancel", defaultValue = "0")
    protected boolean allowedCancel;
    // TODO : Anotacija mozda?
    protected Date lastChangedTime;
    
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
     * Gets the value of the beginDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * Sets the value of the beginDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeginDate(Date value) {
        this.beginDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(Date value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the realised property.
     * 
     */
    public boolean isRealised() {
        return realised;
    }

    /**
     * Sets the value of the realised property.
     * 
     */
    public void setRealised(boolean value) {
        this.realised = value;
    }

    /**
     * Gets the value of the roomDTO property.
     * 
     * @return
     *     possible object is
     *     {@link RoomDTO }
     *     
     */
    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    /**
     * Sets the value of the roomDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomDTO }
     *     
     */
    public void setRoomDTO(RoomDTO value) {
        this.roomDTO = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    /**
     * Gets the value of the allowedCancel property.
     * 
     */
    public boolean isAllowedCancel() {
        return allowedCancel;
    }

    /**
     * Sets the value of the allowedCancel property.
     * 
     */
    public void setAllowedCancel(boolean value) {
        this.allowedCancel = value;
    }

	public Date getLastChangedTime() {
		return lastChangedTime;
	}

	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}
}
