package com.sander.microservices.blacklist.controller;

import com.sander.microservices.blacklist.service.BlacklistCheckHistoryService;
import com.sander.microservices.clients.blacklist.BlackListCheckResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/blacklist-check")
@RequiredArgsConstructor
public class BlacklistController {
    private final BlacklistCheckHistoryService blacklistCheckHistoryService;

    @GetMapping("/{customerId}")
    public BlackListCheckResponse isBlackListed(@PathVariable Long customerId) {
        log.info("Checked for blacklist customer with id: {}", customerId);
        boolean isBlackListedCustomer = blacklistCheckHistoryService.isBlackListedCustomer(customerId);
        return new BlackListCheckResponse(isBlackListedCustomer);
    }
}
