//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.21 at 06:51:34 PM CEST 
//


package com.megatravel.dto.system_user_info;

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
 *         &lt;element name="Rating" type="{http://www.megatravel.com/global_parameters}User_rating"/>
 *         &lt;element name="Comment" type="{http://www.megatravel.com/global_parameters}Description"/>
 *         &lt;element name="Time_stamp" type="{http://www.megatravel.com/global_parameters}Date"/>
 *         &lt;element ref="{http://www.megatravel.com/hotel}RoomDTO" minOccurs="0"/>
 *         &lt;element ref="{http://www.megatravel.com/system_user_info}System_user_infoDTO" minOccurs="0"/>
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
    "comment",
    "timeStamp",
    "roomDTO",
    "systemUserInfoDTO"
})
@XmlRootElement(name = "User_reviewDTO")
public class UserReviewDTO {

    @XmlElement(name = "Id")
    protected long id;
    @XmlElement(name = "Rating")
    protected int rating;
    @XmlElement(name = "Comment", required = true)
    protected String comment;
    @XmlElement(name = "Time_stamp", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    protected Date timeStamp;
    @XmlElement(name = "RoomDTO", namespace = "http://www.megatravel.com/hotel")
    protected RoomDTO roomDTO;
    @XmlElement(name = "System_user_infoDTO")
    protected SystemUserInfoDTO systemUserInfoDTO;

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
    public int getRating() {
        return rating;
    }

    /**
     * Sets the value of the rating property.
     * 
     */
    public void setRating(int value) {
        this.rating = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the timeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the value of the timeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeStamp(Date value) {
        this.timeStamp = value;
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
     * Gets the value of the systemUserInfoDTO property.
     * 
     * @return
     *     possible object is
     *     {@link SystemUserInfoDTO }
     *     
     */
    public SystemUserInfoDTO getSystemUserInfoDTO() {
        return systemUserInfoDTO;
    }

    /**
     * Sets the value of the systemUserInfoDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link SystemUserInfoDTO }
     *     
     */
    public void setSystemUserInfoDTO(SystemUserInfoDTO value) {
        this.systemUserInfoDTO = value;
    }

}
