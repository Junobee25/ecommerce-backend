package com.hanghae.userservice.external.service;

import com.hanghae.userservice.domain.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public String getToken(String email) {
        return tokenRepository.findAllValidTokenByEmail(email).get(0).getAccessToken();
    }
}
