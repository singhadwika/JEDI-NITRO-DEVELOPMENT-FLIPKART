package com.flipfit.bean;

import java.time.LocalDateTime;

/**
 * The Class Waitlist.
 * Represents a user's position on the waitlist for a slot in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class Waitlist {

	/** The waitlist ID. */
	private int waitlistId;
	/** The user ID on the waitlist. */
	private int userId;
    /** The slot ID for which the user is on the waitlist. */
    private int slotId;
    /** The time when the user requested to be on the waitlist. */
    private LocalDateTime requestTime;

    /**
     * Instantiates a new waitlist entry with specified user and slot IDs.
     *
     * @param userId the user id
     * @param slotId the slot id
     */
    public Waitlist(int userId, int slotId) {
    	this.userId = userId;
    	this.slotId = slotId;
    	this.requestTime = LocalDateTime.now();
    }

	/**
     * Gets the waitlist ID.
     *
     * @return the waitlist id
     */
	public int getWaitlistId() {
		return waitlistId;
	}

    /**
     * Sets the waitlist ID.
     *
     * @param waitlistId the waitlist id to set
     */
	public void setWaitlistId(int waitlistId) {
		this.waitlistId = waitlistId;
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
     * Gets the request time for the waitlist.
     *
     * @return the request time
     */
	public LocalDateTime getRequestTime() {
		return requestTime;
	}

    /**
     * Sets the request time for the waitlist.
     *
     * @param requestTime the request time to set
     */
	public void setRequestTime(LocalDateTime requestTime) {
		this.requestTime = requestTime;
	}
}
