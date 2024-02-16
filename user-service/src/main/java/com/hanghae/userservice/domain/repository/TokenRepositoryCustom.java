package com.hanghae.userservice.domain.repository;

import com.hanghae.userservice.domain.entity.Token;

import java.util.List;

public interface TokenRepositoryCustom {
    List<Token> findAllValidTokenByEmail(String email);
}
