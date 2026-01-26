package com.flipfit.bean;

import java.time.LocalDateTime;

public class Waitlist {

	private int waitlistId;
	private int userId;
    private int slotId;
    private LocalDateTime requestTime;

    public Waitlist(int userId, int slotId) {
    	this.userId = userId;
    	this.slotId = slotId;
    	this.requestTime = LocalDateTime.now();
    }

	public int getWaitlistId() {
		return waitlistId;
	}

	public void setWaitlistId(int waitlistId) {
		this.waitlistId = waitlistId;
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

	public LocalDateTime getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(LocalDateTime requestTime) {
		this.requestTime = requestTime;
	}
}
