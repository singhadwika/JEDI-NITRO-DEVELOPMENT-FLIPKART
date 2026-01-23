package com.flipfit.business;

import com.flipfit.bean.Notification;
import java.util.List;

/**
 * Implementation of NotificationServiceInterface.
 */
public class NotificationService implements NotificationServiceInterface {
    
    @Override
    public boolean sendNotification(int userId, String type, String message) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public Notification getNotificationById(int notificationId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<Notification> getNotificationsByUser(int userId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<Notification> getUnreadNotifications(int userId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public boolean markAsRead(int notificationId) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public boolean markAllAsRead(int userId) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public boolean deleteNotification(int notificationId) {
        // TODO: Implement
        return false;
    }
}
