package com.flipfit.dao;

import com.flipfit.bean.Notification;
import java.util.List;

public interface NotificationDAO {

    boolean addNotification(Notification notification);

    Notification getNotificationById(int notificationId);

    List<Notification> getNotificationsByUser(int userId);

    List<Notification> getUnreadNotifications(int userId);

    boolean markAsRead(int notificationId);

    boolean deleteNotification(int notificationId);
}
