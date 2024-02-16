package com.hanghae.userservice.external.controller;

import com.hanghae.userservice.external.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service")
public class TokenController {

    private final TokenService tokenService;

    @GetMapping("/tokens/{email}")
    public String getToken(@PathVariable(value="email") String email) {
        return tokenService.getToken(email);
    }
}
