package com.sander.microservices.customer.controller;

import com.sander.microservices.customer.mapper.CustomerMapper;
import com.sander.microservices.customer.model.CustomerRequestDto;
import com.sander.microservices.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        log.info("Customer registered with customerRequestDto: {}", customerRequestDto);
        customerService.registerCustomer(customerMapper.mapToCustomer(customerRequestDto));
    }

    @GetMapping("/{customerId}/check-blacklist")
    public boolean isBlacklistedCustomer(@PathVariable Long customerId) {
        return customerService.checkBlackListUser(customerId);
    }
}
