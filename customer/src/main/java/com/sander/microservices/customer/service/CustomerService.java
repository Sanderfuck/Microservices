package com.sander.microservices.customer.service;

import com.sander.microservices.clients.blacklist.BlackListCheckResponse;
import com.sander.microservices.clients.blacklist.BlackListClient;
import com.sander.microservices.clients.message.MessageRequest;
import com.sander.microservices.clients.notification.NotificationRequest;
import com.sander.microservices.customer.exception.CustomerNotFoundException;
import com.sander.microservices.customer.model.Customer;
import com.sander.microservices.customer.repository.CustomerRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final BlackListClient blackListClient;
    private final KafkaTemplate<String, Object> myltiTypeKafkaTemplate;

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
            "Welcome to Ukraine - suka"
        );
        MessageRequest messageRequest = new MessageRequest(
            "Message request",
            LocalDateTime.now().toString()
        );

        myltiTypeKafkaTemplate.send("sander", 0, "string", "Customer sent string notification by kafka");

        myltiTypeKafkaTemplate.send("sander", 1, "notification", notificationRequest);

        myltiTypeKafkaTemplate.send("sander", 2, "multi", messageRequest);

        return false;
    }
}
