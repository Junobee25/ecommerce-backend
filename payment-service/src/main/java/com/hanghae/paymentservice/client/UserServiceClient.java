package com.hanghae.paymentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/user-service/users/email")
    String getUserEmail(@RequestHeader HttpHeaders headers);

    @GetMapping("/user-service/users/user-id")
    Long getUserId(@RequestParam("email") String email);

    @GetMapping("/user-service/users/user-info")
    Long getUserInfo(@RequestHeader HttpHeaders headers);
}
