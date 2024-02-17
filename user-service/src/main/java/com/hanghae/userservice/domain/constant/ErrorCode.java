package com.hanghae.userservice.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not founded"),
    USER_NOT_AUTHENTICATED(HttpStatus.UNAUTHORIZED, "User not authenticated"),
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "User name is duplicated"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Password is invalid"),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "User has invalid permission");

    private final HttpStatus status;
    private final String message;
}
