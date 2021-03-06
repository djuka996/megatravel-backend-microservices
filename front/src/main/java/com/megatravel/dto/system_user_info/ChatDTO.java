//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.11 at 09:48:04 PM CEST 
//


package com.megatravel.dto.system_user_info;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.megatravel.dto.hotel.HotelDTO;


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
 *         &lt;element ref="{http://www.megatravel.com/hotel}HotelDTO"/>
 *         &lt;element ref="{http://www.megatravel.com/system_user_info}MessageDTO" maxOccurs="unbounded"/>
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
    "hotelDTO",
    "messages"
})
@XmlRootElement(name = "ChatDTO")
public class ChatDTO {

    @XmlElement(name = "Id")
    protected long id;
    @XmlElement(name = "HotelDTO", namespace = "http://www.megatravel.com/hotel", required = true)
    protected HotelDTO hotelDTO;
    @XmlElement(name = "MessageDTO", required = true)
    protected List<MessageDTO> messages;
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
     * Gets the value of the hotelDTO property.
     * 
     * @return
     *     possible object is
     *     {@link HotelDTO }
     *     
     */
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
     * Gets the value of the messages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageDTO }
     * 
     * 
     */
    public List<MessageDTO> getMessages() {
        if (messages == null) {
            messages = new ArrayList<MessageDTO>();
        }
        return this.messages;
    }

	public Date getLastChangedTime() {
		return lastChangedTime;
	}

	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}

}
