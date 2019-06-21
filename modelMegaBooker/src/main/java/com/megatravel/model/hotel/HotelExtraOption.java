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

@Entity
public class HotelExtraOption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	@ManyToOne()
	protected Hotel hotelExtraOption;
	@ManyToOne()
	protected ExtraOption extraOption;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastChangedTime;
	
	public HotelExtraOption() {

	}

	public HotelExtraOption(Long id, Hotel hotelExtraOption, ExtraOption extraOption) {
		super();
		this.id = id;
		this.hotelExtraOption = hotelExtraOption;
		this.extraOption = extraOption;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hotel getHotelExtraOption() {
		return hotelExtraOption;
	}

	public void setHotelExtraOption(Hotel hotelExtraOption) {
		this.hotelExtraOption = hotelExtraOption;
	}

	public ExtraOption getExtraOption() {
		return extraOption;
	}

	public void setExtraOption(ExtraOption extraOption) {
		this.extraOption = extraOption;
	}

	public Date getLastChangedTime() {
		return lastChangedTime;
	}

	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}

}
