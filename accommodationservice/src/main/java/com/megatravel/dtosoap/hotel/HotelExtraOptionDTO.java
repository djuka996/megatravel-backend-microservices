package com.megatravel.dtosoap.hotel;

import java.util.Date;

import com.megatravel.model.hotel.HotelExtraOption;

public class HotelExtraOptionDTO {

	protected long id;
	protected ExtraOptionDTO extraOptionDTO;
	protected HotelDTO hotelDTO;
	protected Date lastChangedTime;
	
	public HotelExtraOptionDTO() { }
	
	public HotelExtraOptionDTO(HotelExtraOption hotelExtraOption) {
		this.id = hotelExtraOption.getId();
		this.lastChangedTime = hotelExtraOption.getLastChangedTime();
		this.hotelDTO = new HotelDTO(hotelExtraOption.getHotelExtraOption());
		this.extraOptionDTO = new ExtraOptionDTO(hotelExtraOption.getExtraOption());
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public ExtraOptionDTO getExtraOptionDTO() {
		return extraOptionDTO;
	}
	
	public void setExtraOptionDTO(ExtraOptionDTO extraOptionDTO) {
		this.extraOptionDTO = extraOptionDTO;
	}
	
	public HotelDTO getHotelDTO() {
		return hotelDTO;
	}
	
	public void setHotelDTO(HotelDTO hotelDTO) {
		this.hotelDTO = hotelDTO;
	}
	
	public Date getLastChangedTime() {
		return lastChangedTime;
	}
	
	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}
	
}
