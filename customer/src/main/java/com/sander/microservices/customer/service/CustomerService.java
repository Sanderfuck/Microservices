package com.sander.microservices.customer.service;

import com.sander.microservices.amqp.RabbitMQMessageProducer;
import com.sander.microservices.clients.blacklist.BlackListClient;
import com.sander.microservices.clients.notifiction.NotificationClient;
import com.sander.microservices.customer.exception.CustomerNotFoundException;
import com.sander.microservices.customer.model.Customer;
import com.sander.microservices.clients.notifiction.NotificationRequest;
import com.sander.microservices.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.sander.microservices.clients.blacklist.BlackListCheckResponse;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    private final BlackListClient blackListClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        checkBlackListUser(savedCustomer.getId());
    }

    public boolean checkBlackListUser(Long customerId) {
        BlackListCheckResponse checkResponse = blackListClient.isBlackListed(customerId);

        if (checkResponse.isBlackListed()) {
            throw new CustomerNotFoundException("User blacklisted " + customerId);
        }

        NotificationRequest notificationRequest = new NotificationRequest(
            customerId,
            "Welcome to service "
        );

        rabbitMQMessageProducer.publish(
            notificationRequest,
            "internal.exchange",
            "internal.notification.routing-key"
        );
        return false;
    }
}
