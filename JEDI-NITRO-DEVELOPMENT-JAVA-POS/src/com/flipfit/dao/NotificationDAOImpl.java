package com.flipfit.dao;

import com.flipfit.bean.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationDAOImpl implements NotificationDAO {

    private static List<Notification> notificationList = new ArrayList<>();
    private static int notificationCounter = 1;

    @Override
    public boolean addNotification(Notification notification) {

        notification.setNotificationId(notificationCounter++);
        notificationList.add(notification);

        return true;
    }

    @Override
    public Notification getNotificationById(int notificationId) {

        for (Notification notification : notificationList) {
            if (notification.getNotificationId() == notificationId) {
                return notification;
            }
        }

        return null;
    }

    @Override
    public List<Notification> getNotificationsByUser(int userId) {

        List<Notification> result = new ArrayList<>();

        for (Notification notification : notificationList) {
            if (notification.getUserId() == userId) {
                result.add(notification);
            }
        }

        return result;
    }

    @Override
    public List<Notification> getUnreadNotifications(int userId) {

        List<Notification> result = new ArrayList<>();

        for (Notification notification : notificationList) {
            if (notification.getUserId() == userId &&
                notification.getStatus().equalsIgnoreCase("UNREAD")) {

                result.add(notification);
            }
        }

        return result;
    }

    @Override
    public boolean markAsRead(int notificationId) {

        for (Notification notification : notificationList) {
            if (notification.getNotificationId() == notificationId) {

                notification.setStatus("READ");
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteNotification(int notificationId) {

        for (Notification notification : notificationList) {
            if (notification.getNotificationId() == notificationId) {

                notificationList.remove(notification);
                return true;
            }
        }

        return false;
    }
}
