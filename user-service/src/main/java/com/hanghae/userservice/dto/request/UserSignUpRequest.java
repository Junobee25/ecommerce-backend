package com.hanghae.userservice.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public record UserSignUpRequest(
        String email,
        String userName,
        String password,
        String memo,
        MultipartFile profilePicture,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

}
