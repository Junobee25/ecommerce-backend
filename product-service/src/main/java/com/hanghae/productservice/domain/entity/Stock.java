package com.hanghae.productservice.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Long remain;

    private Stock(Long remain) {
        this.remain = remain;
    }

    public static Stock of(Long remain) {
        return new Stock(remain);
    }

    public void decrease(final Long quantity) {
        if ((remain - quantity) < 0) {
            throw new IllegalArgumentException();
        }
        remain -= quantity;
    }
}
