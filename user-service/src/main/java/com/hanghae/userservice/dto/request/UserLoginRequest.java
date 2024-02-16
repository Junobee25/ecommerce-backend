package com.hanghae.userservice.dto.request;

public record UserLoginRequest(
        String email,
        String password
) {
}
