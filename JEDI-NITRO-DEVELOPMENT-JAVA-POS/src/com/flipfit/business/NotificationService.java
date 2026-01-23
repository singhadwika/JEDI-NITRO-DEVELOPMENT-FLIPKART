package com.flipfit.business;

import com.flipfit.bean.Notification;
import java.util.List;

/**
 * Implementation of NotificationServiceInterface.
 */
public class NotificationService implements NotificationServiceInterface {
    
    @Override
    public boolean sendNotification(int userId, String type, String message) {
        System.out.println("NotificationService.sendNotification called");
        return false;
    }
    
    @Override
    public Notification getNotificationById(int notificationId) {
        System.out.println("NotificationService.getNotificationById called");
        return null;
    }
    
    @Override
    public List<Notification> getNotificationsByUser(int userId) {
        System.out.println("NotificationService.getNotificationsByUser called");
        return null;
    }
    
    @Override
    public List<Notification> getUnreadNotifications(int userId) {
        System.out.println("NotificationService.getUnreadNotifications called");
        return null;
    }
    
    @Override
    public boolean markAsRead(int notificationId) {
        System.out.println("NotificationService.markAsRead called");
        return false;
    }
    
    @Override
    public boolean markAllAsRead(int userId) {
        System.out.println("NotificationService.markAllAsRead called");
        return false;
    }
    
    @Override
    public boolean deleteNotification(int notificationId) {
        System.out.println("NotificationService.deleteNotification called");
        return false;
    }
}
