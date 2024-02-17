package com.hanghae.productservice.exception;

import com.hanghae.productservice.domain.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductServiceApplicationException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;

    public ProductServiceApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

    public ProductServiceApplicationException() {

    }

    @Override
    public String getMessage() {
        return (message == null) ? errorCode.getMessage() : String.format("%s. %s", errorCode.getMessage(), message);
    }
}
