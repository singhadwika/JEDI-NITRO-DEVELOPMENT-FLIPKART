package com.flipfit.bean;

import java.time.LocalTime;

/**
 * The Class Slot.
 * Represents a time slot in a gym center of the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class Slot {

	/** The slot ID. */
	private int slotId;
    /** The start time of the slot. */
    private LocalTime startTime;
    /** The end time of the slot. */
    private LocalTime endTime;
    /** The total seats available in the slot. */
    private int totalSeats;
    /** The number of available seats remaining in the slot. */
    private int availableSeats;
    /** The gym center ID this slot belongs to. */
    private int centerId;

    /**
     * Instantiates a new slot with default values.
     */
	public Slot() {}

    /**
     * Instantiates a new slot with specified details.
     *
     * @param slotId the slot id
     * @param startTime the start time
     * @param endTime the end time
     * @param totalSeats the total seats
     * @param availableSeats the available seats
     * @param centerId the center id
     */
    public Slot(int slotId, LocalTime startTime, LocalTime endTime, int totalSeats, int availableSeats, int centerId) {
    	this.slotId = slotId;
    	this.startTime = startTime;
    	this.endTime = endTime;
    	this.totalSeats = totalSeats;
    	this.availableSeats = availableSeats;
    	this.centerId = centerId;
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
     * Gets the start time of the slot.
     *
     * @return the start time
     */
	public LocalTime getStartTime() {
		return startTime;
	}

    /**
     * Sets the start time of the slot.
     *
     * @param startTime the start time to set
     */
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

    /**
     * Gets the end time of the slot.
     *
     * @return the end time
     */
	public LocalTime getEndTime() {
		return endTime;
	}

    /**
     * Sets the end time of the slot.
     *
     * @param endTime the end time to set
     */
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

    /**
     * Gets the total seats in the slot.
     *
     * @return the total seats
     */
	public int getTotalSeats() {
		return totalSeats;
	}

    /**
     * Sets the total seats in the slot.
     *
     * @param totalSeats the total seats to set
     */
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

    /**
     * Gets the number of available seats in the slot.
     *
     * @return the available seats
     */
	public int getAvailableSeats() {
		return availableSeats;
	}

    /**
     * Sets the number of available seats in the slot.
     *
     * @param availableSeats the available seats to set
     */
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

    /**
     * Gets the center ID this slot belongs to.
     *
     * @return the center id
     */
	public int getCenterId() {
		return centerId;
	}

    /**
     * Sets the center ID this slot belongs to.
     *
     * @param centerId the center id to set
     */
	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}    
}
