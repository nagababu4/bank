package com.notification.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.notification.demo.dto.NotificationRequest;
import com.notification.demo.dto.NotificationResponse;
import com.notification.demo.service.NotificationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    public NotificationResponse sendNotification(
            @Valid @RequestBody
            NotificationRequest request) {

        return service.sendNotification(request);
    }

    @GetMapping("/customer/{id}")
    public List<NotificationResponse> getNotifications(
            @PathVariable Long id) {

        return service.getNotifications(id);
    }

    @GetMapping
    public String test() {
        return "Notification Service Running";
    }
}