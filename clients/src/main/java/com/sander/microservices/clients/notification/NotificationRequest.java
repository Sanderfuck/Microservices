package com.sander.microservices.clients.notification;


public record NotificationRequest(
    Long toCustomerId,
    String message
) {
}
