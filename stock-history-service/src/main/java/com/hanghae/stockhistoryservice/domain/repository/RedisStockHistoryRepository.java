package com.hanghae.stockhistoryservice.domain.repository;

import com.hanghae.stockhistoryservice.config.ObjectMapperUtils;
import com.hanghae.stockhistoryservice.domain.entity.StockHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.hanghae.stockhistoryservice.config.ObjectMapperUtils.STOCK_HISTORY_KEY;

@Repository
@RequiredArgsConstructor
public class RedisStockHistoryRepository implements StockHistoryRepository {

    private final ReactiveRedisComponent reactiveRedisComponent;

    @Override
    public Mono<StockHistory> save(StockHistory stockHistory) {
        return reactiveRedisComponent.set(STOCK_HISTORY_KEY,
                "SHK:" +
                        String.valueOf(stockHistory.getProductId()) + ":" +
                        String.valueOf(stockHistory.getUserId()),
                stockHistory).map(sh -> stockHistory);
    }

    @Override
    public Mono<StockHistory> get(String field) {
        return reactiveRedisComponent.get(STOCK_HISTORY_KEY, field).flatMap(sh -> Mono.just(ObjectMapperUtils.objectMapper(sh, StockHistory.class)));
    }

    @Override
    public Flux<StockHistory> getAll() {
        return reactiveRedisComponent.get(STOCK_HISTORY_KEY).map(sh -> ObjectMapperUtils.objectMapper(sh, StockHistory.class))
                .collectList().flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<Long> delete(String id) {
        return reactiveRedisComponent.remove(STOCK_HISTORY_KEY, id);
    }
}
