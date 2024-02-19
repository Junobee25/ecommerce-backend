package com.hanghae.stockhistoryservice.domain.repository;

import com.hanghae.stockhistoryservice.domain.entity.StockHistory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StockHistoryRepository {

    Mono<StockHistory> save(StockHistory stockHistory);

    Mono<StockHistory> get(String key);

    Flux<StockHistory> getAll();

    Mono<Long> delete(String id);
}
