package com.flipfit.business;

import com.flipfit.bean.Notification;
import java.util.List;

public interface NotificationService {
    
    public boolean sendNotification(int userId, String type, String message);
    
    public Notification getNotificationById(int notificationId);
    
    public List<Notification> getNotificationsByUser(int userId);

    public List<Notification> getUnreadNotifications(int userId);    

    public boolean markAsRead(int notificationId);

    public boolean deleteNotification(int notificationId);
}
