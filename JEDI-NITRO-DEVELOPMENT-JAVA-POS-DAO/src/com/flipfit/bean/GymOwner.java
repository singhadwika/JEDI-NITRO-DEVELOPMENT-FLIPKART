package com.flipfit.bean;

import java.util.List;

/**
 * The Class GymOwner.
 * Represents a gym owner user in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class GymOwner extends User {

	/** Indicates whether the gym owner is verified. */
	private boolean isVerified;
    /** The list of gym centers owned by this owner. */
    private List<GymCenter> gymCenters;

    /**
     * Instantiates a new gym owner with default values.
     */
	public GymOwner() {}

    /**
     * Instantiates a new gym owner with specified details.
     *
     * @param id the gym owner id
     * @param name the gym owner name
     * @param email the gym owner email
     * @param password the gym owner password
     * @param isVerified whether the gym owner is verified
     */
    public GymOwner(int id, String name, String email, String password, boolean isVerified) {
    	this.setId(id);
    	this.setName(name);
    	this.setEmail(email);
    	this.setPassword(password);
    	this.isVerified = isVerified;
    }

	/**
     * Checks if the gym owner is verified.
     *
     * @return true if verified, false otherwise
     */
	public boolean isVerified() {
		return isVerified;
	}

    /**
     * Sets the verification status of the gym owner.
     *
     * @param isVerified the verification status to set
     */
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	/**
     * Gets the list of gym centers owned by this owner.
     *
     * @return the gym centers list
     */
	public List<GymCenter> getGymCenters() {
		return gymCenters;
	}

    /**
     * Sets the list of gym centers owned by this owner.
     *
     * @param gymCenters the gym centers list to set
     */
	public void setGymCenters(List<GymCenter> gymCenters) {
		this.gymCenters = gymCenters;
	}
}
