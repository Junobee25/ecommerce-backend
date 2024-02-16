package com.hanghae.userservice.dto.request;

public record UserProfileModifyRequest(
        String userName,
        String memo,
        String profilePicture
) {
}
