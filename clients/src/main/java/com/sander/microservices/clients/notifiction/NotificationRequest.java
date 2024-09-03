package com.sander.microservices.clients.notifiction;

public record NotificationRequest(
    Long toCustomerId,
    String message
) {
}
