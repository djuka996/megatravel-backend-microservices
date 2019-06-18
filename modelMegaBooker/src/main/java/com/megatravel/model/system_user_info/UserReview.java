//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.21 at 06:51:34 PM CEST 
//

package com.megatravel.model.system_user_info;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.megatravel.dto.system_user_info.UserReviewDTO;
import com.megatravel.model.hotel.Room;

@Entity
public class UserReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected int rating;
	protected String comment;
	protected Date timeStamp;
	@OneToOne()
	protected Room room;
	@OneToOne()
	protected User user;
	protected boolean approved;
	public UserReview() {

	}

	public UserReview(UserReviewDTO userReviewDTO) {
		this.id = userReviewDTO.getId();
		this.rating = userReviewDTO.getRating();
		this.comment = userReviewDTO.getComment();
		this.timeStamp = userReviewDTO.getTimeStamp();
		this.room = userReviewDTO.getRoomDTO() != null ? new Room(userReviewDTO.getRoomDTO()) : null;
		this.user = userReviewDTO.getSystemUserInfoDTO() != null ? new User(userReviewDTO.getSystemUserInfoDTO()) : null;
	}

	public UserReview(Long id, int rating, String comment, Date timeStamp, Room room, User user) {
		super();
		this.id = id;
		this.rating = rating;
		this.comment = comment;
		this.timeStamp = timeStamp;
		this.room = room;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	

}
