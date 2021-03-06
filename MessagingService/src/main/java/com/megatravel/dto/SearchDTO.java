package com.megatravel.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SearchDTO {
	private String city;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date beginDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date endDate;
	private int numberOfPeople;
	private boolean advance;
	private String accomodationtype;
	private double category;
	private List<String> additionalService;
	private double distance;
	private String orderByValue; //NONE, PRICE, LOCATION, MARK, CATEGORY
	
	public SearchDTO() {
		
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getNumberOfPeople() {
		return numberOfPeople;
	}
	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}
	public boolean isAdvance() {
		return advance;
	}
	public void setAdvance(boolean advance) {
		this.advance = advance;
	}
	public String getAccomodationtype() {
		return accomodationtype;
	}
	public void setAccomodationtype(String accomodationtype) {
		this.accomodationtype = accomodationtype;
	}
	public double getCategory() {
		return category;
	}
	public void setCategory(double category) {
		this.category = category;
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

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public List<String> getAdditionalService() {
		return additionalService;
	}

	public void setAdditionalService(List<String> additionalService) {
		this.additionalService = additionalService;
	}

	public String getOrderByValue() {
		return orderByValue;
	}

	public void setOrderByValue(String orderByValue) {
		this.orderByValue = orderByValue;
	}
}
