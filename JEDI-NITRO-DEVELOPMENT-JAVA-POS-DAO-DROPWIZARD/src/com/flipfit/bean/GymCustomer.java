package com.flipfit.bean;

import java.util.List;

/**
 * The Class GymCustomer.
 * Represents a gym customer user in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class GymCustomer extends User {

	/** The list of booked slots by this customer. */
	private List<Slot> bookedSlots;

    /**
     * Instantiates a new gym customer with default values.
     */
	public GymCustomer() {}
	
    /**
     * Instantiates a new gym customer with specified details.
     *
     * @param id the customer id
     * @param name the customer name
     * @param email the customer email
     * @param password the customer password
     */
    public GymCustomer(int id, String name, String email, String password) {
    	this.setId(id);
    	this.setName(name);
    	this.setEmail(email);
    	this.setPassword(password);
    }

	/**
     * Gets the list of booked slots.
     *
     * @return the booked slots list
     */
	public List<Slot> getBookedSlots() {
		return bookedSlots;
	}

    /**
     * Sets the list of booked slots.
     *
     * @param bookedSlots the booked slots list to set
     */
	public void setBookedSlots(List<Slot> bookedSlots) {
		this.bookedSlots = bookedSlots;
	}
}
