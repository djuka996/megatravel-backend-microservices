//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.21 at 06:51:34 PM CEST 
//


package com.megatravel.model.room_reservation;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.megatravel.dto.room_reservation.RoomReservationDTO;
import com.megatravel.model.hotel.Room;
import com.megatravel.model.system_user_info.User;
import com.megatravel.model.system_user_info.UserReview;

@Entity
public class RoomReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Basic
	@Temporal(TemporalType.DATE)
    protected Date beginDate;
	@Basic
	@Temporal(TemporalType.DATE)
    protected Date endDate;
    protected boolean realised;
    @ManyToOne()
    protected Room roomReservation;
    protected BigDecimal price;
    @OneToOne()
    protected UserReview userReview;
    @ManyToOne()
    protected User usersReservation;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastChangedTime;
    
    public RoomReservation() {
    	
    }
    
    public RoomReservation(RoomReservationDTO roomReservationDTO) {
    	this.id = roomReservationDTO.getId();
		this.beginDate = roomReservationDTO.getBeginDate();
		this.endDate = roomReservationDTO.getEndDate();
		this.realised = roomReservationDTO.isRealised();
		this.roomReservation = roomReservationDTO.getRoomDTO() != null ? new Room(roomReservationDTO.getRoomDTO()) : null;
		this.price = roomReservationDTO.getPrice();
    }
    
    public RoomReservation(com.megatravel.dtosoap.room_reservation.RoomReservationDTO roomReservationDTO) {
    	this.id = roomReservationDTO.getId();
		this.beginDate = roomReservationDTO.getBeginDate();
		this.endDate = roomReservationDTO.getEndDate();
		this.realised = roomReservationDTO.isRealised();
		this.roomReservation = roomReservationDTO.getRoomDTO() != null ? new Room(roomReservationDTO.getRoomDTO()) : null;
		this.price = roomReservationDTO.getPrice();
    }
    
	public RoomReservation(Long id, Date beginDate, Date endDate, boolean realised, Room roomReservation,
			BigDecimal price, UserReview userReview, User usersReservation) {
		super();
		this.id = id;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.realised = realised;
		this.roomReservation = roomReservation;
		this.price = price;
		this.userReview = userReview;
		this.usersReservation = usersReservation;
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
	public boolean isRealised() {
		return realised;
	}
	public void setRealised(boolean realised) {
		this.realised = realised;
	}
	public Room getRoomReservation() {
		return roomReservation;
	}
	public void setRoomReservation(Room roomReservation) {
		this.roomReservation = roomReservation;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public UserReview getUserReview() {
		return userReview;
	}
	public void setUserReview(UserReview userReview) {
		this.userReview = userReview;
	}
	public User getUsersReservation() {
		return usersReservation;
	}
	public void setUsersReservation(User usersReservation) {
		this.usersReservation = usersReservation;
	}

	public Date getLastChangedTime() {
		return lastChangedTime;
	}

	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}
}
