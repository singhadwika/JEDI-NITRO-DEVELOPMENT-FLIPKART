package com.flipfit.bean;

import java.time.LocalDateTime;

/**
 * The Class Notification.
 * Represents a notification sent to users in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class Notification {

	/** The notification ID. */
	private int notificationId;
    /** The notification type. */
    private String type;
    /** The notification message. */
    private String message;
    /** The notification status. */
    private String status;
    /** The creation time of the notification. */
    private LocalDateTime createdAt;
    /** The user ID associated with the notification. */
    private int userId;
	
    /**
     * Gets the notification ID.
     *
     * @return the notification id
     */
    public int getNotificationId() {
		return notificationId;
	}
	
	/**
     * Sets the notification ID.
     *
     * @param notificationId the notification id to set
     */
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	
    /**
     * Gets the notification type.
     *
     * @return the type
     */
	public String getType() {
		return type;
	}
	
    /**
     * Sets the notification type.
     *
     * @param type the type to set
     */
	public void setType(String type) {
		this.type = type;
	}
	
    /**
     * Gets the notification message.
     *
     * @return the message
     */
	public String getMessage() {
		return message;
	}
	
    /**
     * Sets the notification message.
     *
     * @param message the message to set
     */
	public void setMessage(String message) {
		this.message = message;
	}
	
    /**
     * Gets the notification status.
     *
     * @return the status
     */
	public String getStatus() {
		return status;
	}
	
    /**
     * Sets the notification status.
     *
     * @param status the status to set
     */
	public void setStatus(String status) {
		this.status = status;
	}
	
    /**
     * Gets the creation time of the notification.
     *
     * @return the created at
     */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	/**
     * Sets the creation time of the notification.
     *
     * @param createdAt the created at time to set
     */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
    /**
     * Gets the user ID associated with the notification.
     *
     * @return the user id
     */
	public int getUserId() {
		return userId;
	}
	
    /**
     * Sets the user ID associated with the notification.
     *
     * @param userId the user id to set
     */
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
