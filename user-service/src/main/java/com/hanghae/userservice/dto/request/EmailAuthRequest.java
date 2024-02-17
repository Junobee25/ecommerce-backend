package com.hanghae.userservice.dto.request;

public record EmailAuthRequest(
        String email,
        String authToken
) {
}
