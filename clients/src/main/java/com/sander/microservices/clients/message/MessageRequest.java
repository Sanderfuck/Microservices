package com.sander.microservices.clients.message;


public record MessageRequest(
    String message,
    String sendTime
) {
}
