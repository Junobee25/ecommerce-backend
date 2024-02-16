package com.hanghae.userservice.domain.entity;

import com.hanghae.userservice.domain.constant.TokenType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String accessToken;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @Getter
    @Setter
    private Boolean expired;

    @Getter
    @Setter
    private Boolean revoked;

    private String email;
}
