package com.sander.microservices.customer.mapper;

import com.sander.microservices.customer.model.Customer;
import com.sander.microservices.customer.model.CustomerRequestDto;
import org.springframework.stereotype.Component;

@Component
public record CustomerMapper() {

    public Customer mapToCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = Customer.builder()
            .firstName(customerRequestDto.firstName())
            .lastName(customerRequestDto.lastName())
            .email(customerRequestDto.email())
            .build();

        return customer;
    }
}
