package com.notification.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationRequest {

    @NotNull
    private Long customerId;

    @NotBlank
    private String title;

    @NotBlank
    private String message;
}