package com.flipfit.bean;

import java.time.LocalDate;

public class Booking {
	
    private int bookingId;
    private LocalDate bookingDate;
    private boolean status;
    private int userId;
    private int slotId;
    private int centerId;

    public Booking(int bookingId, LocalDate bookingDate, boolean status, int userId, int slotId, int centerId) {
    	this.bookingId = bookingId;
    	this.bookingDate = bookingDate;
    	this.status = status;
    	this.userId = userId;
    	this.slotId = slotId;
    	this.centerId = centerId;
    }

    public int getBookingId() {
    	return bookingId;
    }
    
    public void setBookingId(int bookingId) {
    	this.bookingId = bookingId;
    }
    
    public LocalDate getBookingDate() {
    	return bookingDate; 
    }
    
    public void setBookingDate(LocalDate bookingDate) {
    	this.bookingDate = bookingDate;
    }

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}
}
