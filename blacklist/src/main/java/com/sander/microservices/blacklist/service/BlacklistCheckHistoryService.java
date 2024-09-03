package com.sander.microservices.blacklist.service;

import com.sander.microservices.blacklist.model.BlacklistCheckHistory;
import com.sander.microservices.blacklist.repository.BlacklistCheckHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlacklistCheckHistoryService {
    private final BlacklistCheckHistoryRepository  blacklistCheckHistoryRepository;

    public boolean isBlackListedCustomer(Long customerId) {
        blacklistCheckHistoryRepository.save(
            BlacklistCheckHistory.builder()
                .customerId(customerId)
                .isBlacklisted(false)
                .build()
        );

        return false;
    }
}
