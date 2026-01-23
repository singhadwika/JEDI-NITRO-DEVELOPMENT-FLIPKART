package com.flipfit.bean;

import java.time.LocalDateTime;

public class Notification {
    private int notificationId;
    private String type;
    private String message;
    private String status;
    private LocalDateTime createdAt;
    private int userId;

    public Notification() {}

    public Notification(int notificationId, String type, String message, int userId) {}

    public Notification(int notificationId, String type, String message, String status, LocalDateTime createdAt, int userId) {}

    public int getNotificationId() { return 0; }
    public void setNotificationId(int notificationId) {}
    public String getType() { return null; }
    public void setType(String type) {}
    public String getMessage() { return null; }
    public void setMessage(String message) {}
    public String getStatus() { return null; }
    public void setStatus(String status) {}
    public LocalDateTime getCreatedAt() { return null; }
    public void setCreatedAt(LocalDateTime createdAt) {}
    public int getUserId() { return 0; }
    public void setUserId(int userId) {}

    public boolean send() { return false; }
    public void markAsRead() {}
    public boolean isRead() { return false; }
}
