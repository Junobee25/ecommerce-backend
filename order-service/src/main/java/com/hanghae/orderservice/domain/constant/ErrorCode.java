package com.hanghae.orderservice.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "User has invalid permission"),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "Order not founded");

    private final HttpStatus httpStatus;
    private final String message;

}
