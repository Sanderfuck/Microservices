package com.sander.microservices.notification;

import com.sander.microservices.amqp.RabbitMQMessageProducer;
import com.sander.microservices.notification.configuration.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
    scanBasePackages = {
        "com.sander.microservices.notification",
        "com.sander.microservices.amqp"
    }
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer, NotificationConfig config) {
//        return args -> {
//            producer.publish("foo", config.getInternalExchange(), config.getInternalNotificationRoutingKey());
//        };
//    }
}