package com.hanghae.userservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghae.userservice.configuration.service.JwtService;
import com.hanghae.userservice.domain.constant.ErrorCode;
import com.hanghae.userservice.domain.constant.TokenType;
import com.hanghae.userservice.domain.entity.EmailAuth;
import com.hanghae.userservice.domain.entity.Token;
import com.hanghae.userservice.domain.entity.UserAccount;
import com.hanghae.userservice.domain.repository.EmailAuthRepository;
import com.hanghae.userservice.domain.repository.TokenRepository;
import com.hanghae.userservice.domain.repository.UserAccountRepository;
import com.hanghae.userservice.dto.UserAccountDto;
import com.hanghae.userservice.dto.response.UserLoginResponse;
import com.hanghae.userservice.exception.UserServiceApplicationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final EmailAuthService emailAuthService;
    private final EmailAuthRepository emailAuthRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder;
    private final TokenRepository tokenRepository;

    public UserAccount loadUserByEmail(String email) throws UserServiceApplicationException {
        return userAccountRepository.findByEmail(email).orElseThrow(
                () -> new UserServiceApplicationException(ErrorCode.USER_NOT_FOUND, String.format("email is %s", email))
        );
    }
    public UserAccountDto signUp(String email, String userName, String userPassword, String memo, MultipartFile profilePicture) {

        EmailAuth emailAuth = emailAuthRepository.save(
                EmailAuth.builder()
                        .email(email)
                        .authToken(UUID.randomUUID().toString())
                        .expired(false)
                        .build());

        userAccountRepository.findByEmail(email).ifPresent(it -> {
            throw new RuntimeException();
        });

        String profilePictureBase64 = null;
        if (profilePicture != null && !profilePicture.isEmpty()) {
            try {
                byte[] bytes = profilePicture.getBytes();
                profilePictureBase64 = Base64.getEncoder().encodeToString(bytes);
            } catch (IOException e) {
                //TODO: Logger
                e.printStackTrace();
            }
        }
        profilePictureBase64 = "/temp/img";
        UserAccount savedUser = userAccountRepository.save(UserAccount.of(email, userName, encoder.encode(userPassword), false, memo, profilePictureBase64));
        emailAuthService.send(emailAuth.getEmail(), emailAuth.getAuthToken());
        return UserAccountDto.from(savedUser);
    }

    @Transactional // JPA에서 영속성 컨텍스트 통해 객체 변경 추적하고 트랜잭션 커밋 시점에 DB에 반영해줌.
    public void confirmEmail(String email, String authToken) {
        EmailAuth emailAuth = emailAuthRepository.findValidAuthByEmail(email, authToken, LocalDateTime.now())
                .orElseThrow(UserServiceApplicationException::new);
        UserAccount userAccount = userAccountRepository.findByEmail(email)
                .orElseThrow(UserServiceApplicationException::new);
        emailAuth.userToken();
        //TODO: Setter로 대체 됨 뭘 사용할지에 대한 기준은 잘 모르겠음
        userAccount.emailVerifiedSuccess();
    }

    public UserLoginResponse login(String email, String password) {
        UserAccount userAccount = userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new UserServiceApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", email)));

        if (!encoder.matches(password, userAccount.getUserPassword())) {
            throw new UserServiceApplicationException(ErrorCode.INVALID_PASSWORD);
        }
        if (!userAccount.getEmailVerified()) {
            throw new UserServiceApplicationException(ErrorCode.USER_NOT_AUTHENTICATED);
        }

        String accessToken = jwtService.generateToken(email);
        String refreshToken = jwtService.generateRefreshToken(email);
        revokeAllUserTokens(email);
        saveToken(email, accessToken);
        return new UserLoginResponse(accessToken, refreshToken);
    }

    @Transactional
    public void modifyProfile(String email, String userName, String memo, String profilePicture) {
        UserAccount userAccount = userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new UserServiceApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", email)));

        userAccount.setUserName(userName);
        userAccount.setMemo(memo);
        userAccount.setProfilePicture(profilePicture);
        userAccountRepository.save(userAccount);
    }

    @Transactional
    public UserLoginResponse modifyPassword(String email, String currentPassword, String newPassword) {
        UserAccount userAccount = userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new UserServiceApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", email)));

        if (!encoder.matches(currentPassword, userAccount.getUserPassword())) {
            throw new UserServiceApplicationException(ErrorCode.INVALID_PASSWORD);
        }

        userAccount.setUserPassword(encoder.encode(newPassword));
        userAccountRepository.save(userAccount);

        String accessToken = jwtService.generateToken(email);
        String refreshToken = jwtService.generateRefreshToken(email);
        revokeAllUserTokens(email);
        saveToken(email, accessToken);
        return new UserLoginResponse(accessToken, refreshToken);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String email;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        email = jwtService.extractEmail(refreshToken);
        if (email != null) {
            UserAccount userAccount = userAccountRepository.findByEmail(email)
                    .orElseThrow(() -> new UserServiceApplicationException(ErrorCode.USER_NOT_FOUND, String.format("email is %s", email)));
            if (jwtService.isTokenValid(refreshToken, userAccount)) {
                String accessToken = jwtService.generateToken(userAccount.getEmail());
                UserLoginResponse userLoginResponse = new UserLoginResponse(accessToken, refreshToken);
                new ObjectMapper().writeValue(response.getOutputStream(), userLoginResponse);
            }
        }
    }

    private void revokeAllUserTokens(String email) {
        List<Token> validTokens = tokenRepository.findAllValidTokenByEmail(email);
        if (!validTokens.isEmpty()) {
            validTokens.forEach(t -> {
                t.setExpired(true);
                t.setRevoked(true);
            });
        }
    }

    private void saveToken(String email, String accessToken) {
        Token token = Token.builder()
                .accessToken(accessToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .email(email)
                .build();
        tokenRepository.save(token);
    }

    public List<String> allUserEmail() {
        return userAccountRepository.findAll().stream()
                .map(UserAccount::getEmail)
                .collect(Collectors.toList());
    }
}
