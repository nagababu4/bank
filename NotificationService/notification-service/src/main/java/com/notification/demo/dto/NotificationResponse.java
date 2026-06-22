package com.notification.demo.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationResponse {

    private Long notificationId;

    private Long customerId;

    private String title;

    private String message;

    private LocalDateTime sentAt;
}