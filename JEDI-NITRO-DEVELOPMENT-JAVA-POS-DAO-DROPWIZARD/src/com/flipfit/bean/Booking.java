package com.flipfit.bean;

import java.time.LocalDate;

/**
 * The Class Booking.
 * Represents a booking made by a customer in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class Booking {
	
    /** The booking ID. */
    private int bookingId;
    /** The booking date. */
    private LocalDate bookingDate;
    /** The booking status. */
    private boolean status;
    /** The user ID associated with the booking. */
    private int userId;
    /** The slot ID for the booking. */
    private int slotId;
    /** The gym center ID for the booking. */
    private int centerId;

    /**
     * Instantiates a new booking with default values.
     */
	public Booking() {}

    /**
     * Instantiates a new booking with specified details.
     *
     * @param bookingId the booking id
     * @param bookingDate the booking date
     * @param status the booking status
     * @param userId the user id
     * @param slotId the slot id
     * @param centerId the center id
     */
    public Booking(int bookingId, LocalDate bookingDate, boolean status, int userId, int slotId, int centerId) {
    	this.bookingId = bookingId;
    	this.bookingDate = bookingDate;
    	this.status = status;
    	this.userId = userId;
    	this.slotId = slotId;
    	this.centerId = centerId;
    }

    /**
     * Gets the booking ID.
     *
     * @return the booking id
     */
    public int getBookingId() {
    	return bookingId;
    }
    
    /**
     * Sets the booking ID.
     *
     * @param bookingId the booking id to set
     */
    public void setBookingId(int bookingId) {
    	this.bookingId = bookingId;
    }
    
    /**
     * Gets the booking date.
     *
     * @return the booking date
     */
    public LocalDate getBookingDate() {
    	return bookingDate; 
    }
    
    /**
     * Sets the booking date.
     *
     * @param bookingDate the booking date to set
     */
    public void setBookingDate(LocalDate bookingDate) {
    	this.bookingDate = bookingDate;
    }

    /**
     * Checks if the booking is active.
     *
     * @return the booking status
     */
	public boolean isStatus() {
		return status;
	}

	/**
     * Sets the booking status.
     *
     * @param status the status to set
     */
	public void setStatus(boolean status) {
		this.status = status;
	}

    /**
     * Gets the user ID.
     *
     * @return the user id
     */
	public int getUserId() {
		return userId;
	}

	/**
     * Sets the user ID.
     *
     * @param userId the user id to set
     */
	public void setUserId(int userId) {
		this.userId = userId;
	}

    /**
     * Gets the slot ID.
     *
     * @return the slot id
     */
	public int getSlotId() {
		return slotId;
	}

    /**
     * Sets the slot ID.
     *
     * @param slotId the slot id to set
     */
	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	/**
     * Gets the center ID.
     *
     * @return the center id
     */
	public int getCenterId() {
		return centerId;
	}

    /**
     * Sets the center ID.
     *
     * @param centerId the center id to set
     */
	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}
}
