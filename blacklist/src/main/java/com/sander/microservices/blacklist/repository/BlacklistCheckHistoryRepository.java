package com.sander.microservices.blacklist.repository;

import com.sander.microservices.blacklist.model.BlacklistCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistCheckHistoryRepository extends JpaRepository<BlacklistCheckHistory, Long> {

}
