package com.flipfit.bean;

import java.util.List;

/**
 * The Class GymCenter.
 * Represents a gym center in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class GymCenter {
	
    /** The center ID. */
    private int centerId;
    /** The center name. */
    private String name;
    /** The center location. */
    private String location;
    /** The list of slots available at the center. */
    private List<Slot> slots;
    /** Indicates whether the center is approved. */
    private boolean approved;
    /** The owner ID of the center. */
    private int ownerId;

    /**
     * Instantiates a new gym center with default values.
     */
	public GymCenter() {}

    /**
     * Instantiates a new gym center with specified details.
     *
     * @param centerId the center id
     * @param name the center name
     * @param location the center location
     * @param approved whether the center is approved
     * @param ownerId the owner id
     */
    public GymCenter(int centerId, String name, String location, boolean approved, int ownerId) {
    	this.centerId = centerId;
    	this.name = name;
    	this.location = location;
    	this.approved = approved;
    	this.ownerId = ownerId;
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

    /**
     * Gets the center name.
     *
     * @return the center name
     */
	public String getName() {
		return name;
	}

    /**
     * Sets the center name.
     *
     * @param name the center name to set
     */
	public void setName(String name) {
		this.name = name;
	}

    /**
     * Gets the center location.
     *
     * @return the center location
     */
	public String getLocation() {
		return location;
	}

    /**
     * Sets the center location.
     *
     * @param location the center location to set
     */
	public void setLocation(String location) {
		this.location = location;
	}

    /**
     * Gets the slots available at the center.
     *
     * @return the slots list
     */
	public List<Slot> getSlots() {
		return slots;
	}

    /**
     * Sets the slots available at the center.
     *
     * @param slots the slots list to set
     */
	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}

    /**
     * Checks if the center is approved.
     *
     * @return true if approved, false otherwise
     */
	public boolean isApproved() {
		return approved;
	}

    /**
     * Sets the approval status of the center.
     *
     * @param approved the approval status to set
     */
	public void setApproved(boolean approved) {
		this.approved = approved;
	}

    /**
     * Gets the owner ID.
     *
     * @return the owner id
     */
	public int getOwnerId() {
		return ownerId;
	}

    /**
     * Sets the owner ID.
     *
     * @param ownerId the owner id to set
     */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
}
