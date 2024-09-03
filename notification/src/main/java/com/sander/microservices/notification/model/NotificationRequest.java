package com.sander.microservices.notification.model;

public record NotificationRequest(
    Long toCustomerId,
    String message
) {
}
