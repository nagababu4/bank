package com.notification.demo.service;

import java.util.List;

import com.notification.demo.dto.NotificationRequest;
import com.notification.demo.dto.NotificationResponse;

public interface NotificationService {

    NotificationResponse sendNotification(
            NotificationRequest request);

    List<NotificationResponse> getNotifications(
            Long customerId);
}