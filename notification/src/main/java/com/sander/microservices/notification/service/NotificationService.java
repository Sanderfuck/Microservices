package com.sander.microservices.notification.service;

import com.sander.microservices.notification.model.Notification;
import com.sander.microservices.notification.model.NotificationRequest;
import com.sander.microservices.notification.repository.NotificationRepository;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
            Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .sender("Sander")
                .message(notificationRequest.message())
                .sentAt(LocalDateTime.now())
                .build()
        );
    }
}
