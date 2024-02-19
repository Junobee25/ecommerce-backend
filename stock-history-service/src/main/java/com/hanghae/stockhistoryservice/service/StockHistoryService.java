package com.hanghae.stockhistoryservice.service;

import com.hanghae.stockhistoryservice.domain.entity.StockHistory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StockHistoryService {

    Mono<StockHistory> create(StockHistory stockHistory);

    Flux<StockHistory> getAll();

    Mono<StockHistory> getOne(String id);

    Mono<Long> deleteById(String id);
}
