package com.hanghae.userservice.dto.response;

public record UserLoginResponse(
        String accessToken,
        String refreshToken
) {
}
