package com.flipfit.bean;

import java.time.LocalTime;

public class Slot {

	private int slotId;
    private LocalTime startTime;
    private LocalTime endTime;
    private int totalSeats;
    private int availableSeats;
    private int centerId;

    public Slot(int slotId, LocalTime startTime, LocalTime endTime, int totalSeats, int availableSeats, int centerId) {
    	this.slotId = slotId;
    	this.startTime = startTime;
    	this.endTime = endTime;
    	this.totalSeats = totalSeats;
    	this.availableSeats = availableSeats;
    	this.centerId = centerId;
    }

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}    
}
