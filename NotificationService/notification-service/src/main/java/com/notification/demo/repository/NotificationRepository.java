package com.notification.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notification.demo.entity.Notification;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    List<Notification> findByCustomerId(Long customerId);
}