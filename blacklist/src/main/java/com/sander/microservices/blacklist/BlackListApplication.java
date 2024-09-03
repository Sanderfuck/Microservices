package com.sander.microservices.blacklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BlackListApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlackListApplication.class, args);
    }
}