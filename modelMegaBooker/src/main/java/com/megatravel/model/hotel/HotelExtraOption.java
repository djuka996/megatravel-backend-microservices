package com.megatravel.model.hotel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class HotelExtraOption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	@ManyToOne()
	protected Hotel hotelExtraOption;
	@ManyToOne()
	protected ExtraOption extraOption;

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

}
