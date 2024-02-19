package com.hanghae.paymentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
@RequestMapping("/user-service/users")
public interface UserServiceClient {

    @GetMapping("/email")
    String getUserEmail(@RequestHeader HttpHeaders headers);

    @GetMapping("/user-id")
    Long getUserId(@RequestParam("email") String email);
}
