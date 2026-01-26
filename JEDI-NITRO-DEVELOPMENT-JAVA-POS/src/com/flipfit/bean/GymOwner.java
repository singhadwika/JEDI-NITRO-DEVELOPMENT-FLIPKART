package com.flipfit.bean;

import java.util.List;

public class GymOwner extends User {

	private boolean isVerified;
    private List<GymCenter> gymCenters;

    public GymOwner(int id, String name, String email, String password, boolean isVerified) {
    	this.setId(id);
    	this.setName(name);
    	this.setEmail(email);
    	this.setPassword(password);
    	this.isVerified = isVerified;
    }

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public List<GymCenter> getGymCenters() {
		return gymCenters;
	}

	public void setGymCenters(List<GymCenter> gymCenters) {
		this.gymCenters = gymCenters;
	}
}
