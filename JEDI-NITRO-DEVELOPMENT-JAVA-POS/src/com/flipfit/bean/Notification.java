package com.flipfit.bean;

import java.time.LocalDateTime;

public class Notification {

	private int notificationId;
    private String type;
    private String message;
    private String status;
    private LocalDateTime createdAt;
    private int userId;
	
    public int getNotificationId() {
		return notificationId;
	}
	
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
