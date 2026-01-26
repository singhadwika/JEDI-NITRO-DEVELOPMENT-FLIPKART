package com.flipfit.bean;

import java.util.List;

public class GymCustomer extends User {

	private List<Slot> bookedSlots;

	public GymCustomer() {}

    public GymCustomer(int id, String name, String email, String password) {
    	this.setId(id);
    	this.setName(name);
    	this.setEmail(email);
    	this.setPassword(password);
    }

	public List<Slot> getBookedSlots() {
		return bookedSlots;
	}

	public void setBookedSlots(List<Slot> bookedSlots) {
		this.bookedSlots = bookedSlots;
	}
}
