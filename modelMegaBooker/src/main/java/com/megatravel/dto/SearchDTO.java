package com.megatravel.dto;

public class SearchDTO {
	private String city;
	private String beginDate;
	private String endDate;
	private int numberOfPeople;
	private boolean advance;
	private String accomodationtype;
	private double category;
	private String additionalService;
	
	public SearchDTO() {
		
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public String getAdditionalService() {
		return additionalService;
	}
	public void setAdditionalService(String additionalService) {
		this.additionalService = additionalService;
	}
}
