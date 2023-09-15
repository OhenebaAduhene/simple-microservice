package com.addy.app.service;

import com.addy.app.entity.Notification;
import com.addy.app.notification.NotificationRequest;
import com.addy.app.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationRepository repository) {

    public void notificationRequest(NotificationRequest request) {
        repository.save(Notification.builder().message(request.message())
                .toCustomerId(request.toCustomerId())
                .toCustomerEmail(request.toCustomerEmail())
                .sender("Addy")
                .sentAt(LocalDateTime.now())
                .build()
        );
    }
}
