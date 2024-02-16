package com.hanghae.userservice.exception;

import com.hanghae.userservice.domain.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserServiceApplicationException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;

    public UserServiceApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

    public UserServiceApplicationException() {

    }

    @Override
    public String getMessage() {
        return (message == null) ? errorCode.getMessage() : String.format("%s. %s", errorCode.getMessage(), message);
    }
}
