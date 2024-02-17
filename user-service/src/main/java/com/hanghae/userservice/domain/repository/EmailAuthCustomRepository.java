package com.hanghae.userservice.domain.repository;

import com.hanghae.userservice.domain.entity.EmailAuth;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailAuthCustomRepository {

    Optional<EmailAuth> findValidAuthByEmail(String email, String authToken, LocalDateTime currentTime);

}
