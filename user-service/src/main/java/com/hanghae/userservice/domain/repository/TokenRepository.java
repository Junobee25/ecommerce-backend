package com.hanghae.userservice.domain.repository;

import com.hanghae.userservice.domain.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long>, TokenRepositoryCustom {
    Optional<Token> findByAccessToken(String token);
}
