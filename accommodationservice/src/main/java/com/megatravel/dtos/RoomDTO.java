package com.megatravel.dtos;

public class RoomDTO {

	private Long id;

	private String description;

	private int cancellationDays;

	private boolean cancellationAllowed;

	private int capacity;

	private int numberOfBeds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCancellationDays() {
		return cancellationDays;
	}

	public void setCancellationDays(int cancellationDays) {
		this.cancellationDays = cancellationDays;
	}

	public boolean isCancellationAllowed() {
		return cancellationAllowed;
	}

	public void setCancellationAllowed(boolean cancellationAllowed) {
		this.cancellationAllowed = cancellationAllowed;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

}
