package com.notification.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.notification.demo.dto.NotificationRequest;
import com.notification.demo.dto.NotificationResponse;
import com.notification.demo.entity.Notification;
import com.notification.demo.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl
        implements NotificationService {

    private final NotificationRepository repository;

    @Override
    public NotificationResponse sendNotification(
            NotificationRequest request) {

        Notification notification =
                Notification.builder()
                        .customerId(request.getCustomerId())
                        .title(request.getTitle())
                        .message(request.getMessage())
                        .sentAt(LocalDateTime.now())
                        .build();

        Notification saved =
                repository.save(notification);

        return map(saved);
    }

    @Override
    public List<NotificationResponse> getNotifications(
            Long customerId) {

        return repository.findByCustomerId(customerId)
                .stream()
                .map(this::map)
                .toList();
    }

    private NotificationResponse map(
            Notification notification) {

        return NotificationResponse.builder()
                .notificationId(notification.getNotificationId())
                .customerId(notification.getCustomerId())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .sentAt(notification.getSentAt())
                .build();
    }
}