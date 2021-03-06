//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.21 at 06:51:34 PM CEST 
//


package com.megatravel.model.hotel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.megatravel.dto.hotel.ImageDTO;

@Entity
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String filePath;
    @ManyToOne()
    protected Room roomImage;
    @ManyToOne()
    protected Hotel hotel;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastChangedTime;
    
    public Image() {
    	
    }
    
    public Image(ImageDTO imageDTO) {
		this.id = imageDTO.getId();
		this.filePath = imageDTO.getFilePath();
	}
    
	public Image(Long id, String filePath, Room roomImage, Hotel hotel) {
		super();
		this.id = id;
		this.filePath = filePath;
		this.roomImage = roomImage;
		this.hotel = hotel;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Room getRoomImage() {
		return roomImage;
	}
	public void setRoomImage(Room roomImage) {
		this.roomImage = roomImage;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Date getLastChangedTime() {
		return lastChangedTime;
	}

	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}
}
