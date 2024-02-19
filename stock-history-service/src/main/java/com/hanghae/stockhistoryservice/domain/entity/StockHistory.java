package com.hanghae.stockhistoryservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockHistory {

    @Id
    private Long id;

    private Long productId;

    private Long userId;

    private Integer quantity;

}
