package com.flipfit.business;

import com.flipfit.bean.Notification;
import com.flipfit.dao.NotificationDAO;
import com.flipfit.dao.NotificationDAOImpl;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationServiceImpl implements NotificationService {

    private NotificationDAO notificationDAO = new NotificationDAOImpl();

    @Override
    public boolean sendNotification(int userId, String type, String message) {

        Notification notification = new Notification();

        notification.setUserId(userId);
        notification.setType(type);
        notification.setMessage(message);
        notification.setStatus("UNREAD");
        notification.setCreatedAt(LocalDateTime.now());

        return notificationDAO.addNotification(notification);
    }

    @Override
    public Notification getNotificationById(int notificationId) {
        return notificationDAO.getNotificationById(notificationId);
    }

    @Override
    public List<Notification> getNotificationsByUser(int userId) {
        return notificationDAO.getNotificationsByUser(userId);
    }

    @Override
    public List<Notification> getUnreadNotifications(int userId) {
        return notificationDAO.getUnreadNotifications(userId);
    }

    @Override
    public boolean markAsRead(int notificationId) {
        return notificationDAO.markAsRead(notificationId);
    }

    @Override
    public boolean deleteNotification(int notificationId) {
        return notificationDAO.deleteNotification(notificationId);
    }
}
