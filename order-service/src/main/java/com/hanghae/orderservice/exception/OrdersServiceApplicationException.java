package com.hanghae.orderservice.exception;

import com.hanghae.orderservice.domain.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrdersServiceApplicationException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;

    public OrdersServiceApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

    public OrdersServiceApplicationException() {

    }

    @Override
    public String getMessage() {
        return (message == null) ? errorCode.getMessage() : String.format("%s. %s", errorCode.getMessage(), message);
    }
}
