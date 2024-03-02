package com.hanghae.userservice.external.service;

import com.hanghae.userservice.domain.constant.ErrorCode;
import com.hanghae.userservice.domain.entity.UserAccount;
import com.hanghae.userservice.domain.repository.UserAccountRepository;
import com.hanghae.userservice.exception.UserServiceApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExternalService {

    private final UserAccountRepository userAccountRepository;

    public Long getUserId(String email) {
        return userAccountRepository.findByEmail(email)
                .map(UserAccount::getId)
                .orElseThrow(() -> new UserServiceApplicationException(ErrorCode.USER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Long getUserInfo(String email) {
        return userAccountRepository.findByEmail(email)
                .map(UserAccount::getId)
                .orElseThrow(() -> new UserServiceApplicationException(ErrorCode.USER_NOT_FOUND));
    }
}
