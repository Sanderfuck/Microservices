package com.sander.microservices.customer.model;

public record CustomerRequestDto(
    String firstName,
    String lastName,
    String email
) {
}
