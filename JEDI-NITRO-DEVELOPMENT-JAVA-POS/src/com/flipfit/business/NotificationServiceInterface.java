package com.flipfit.business;

import com.flipfit.bean.Notification;
import java.util.List;

public interface NotificationServiceInterface {
    boolean sendNotification(int userId, String type, String message);

    Notification getNotificationById(int notificationId);

    List<Notification> getNotificationsByUser(int userId);

    List<Notification> getUnreadNotifications(int userId);

    boolean markAsRead(int notificationId);

    boolean markAllAsRead(int userId);

    boolean deleteNotification(int notificationId);
}

