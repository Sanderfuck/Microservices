package com.sander.microservices.clients.blacklist;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "blacklist",
    url = "${clients.blacklist.url}"
)
public interface BlackListClient {

    @GetMapping(path = "api/v1/blacklist-check/{customerId}")
    BlackListCheckResponse isBlackListed(@PathVariable("customerId") Long customerId);

}
