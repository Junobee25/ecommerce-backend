package com.hanghae.userservice.domain.repository;

import com.hanghae.userservice.domain.entity.QToken;
import com.hanghae.userservice.domain.entity.Token;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TokenRepositoryCustomImpl implements TokenRepositoryCustom{

    JPAQueryFactory jpaQueryFactory;

    public TokenRepositoryCustomImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    //TODO: email로 select를 보내면 일치하는 이메일을 가져오므로 유효한 정보에 대해서만 가져올 수 있도록 리팩토링 필요
    @Override
    public List<Token> findAllValidTokenByEmail(String email) {
        return jpaQueryFactory
                .selectFrom(QToken.token)
                .where(
                        QToken.token.email.eq(email)
                                .and(QToken.token.expired.eq(false))
                                .and(QToken.token.revoked.eq(false)))
                .fetch();
    }
}
