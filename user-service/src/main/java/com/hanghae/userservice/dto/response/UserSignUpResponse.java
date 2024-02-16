package com.hanghae.userservice.dto.response;

import com.hanghae.userservice.dto.UserAccountDto;

public record UserSignUpResponse(
        Long id,
        String email
) {
    public static UserSignUpResponse from(UserAccountDto dto) {
        return new UserSignUpResponse(
                dto.id(),
                dto.email()
        );
    }
}
