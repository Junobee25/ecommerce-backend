package com.hanghae.stockhistoryservice.service;

import com.hanghae.stockhistoryservice.domain.entity.StockHistory;
import com.hanghae.stockhistoryservice.domain.repository.RedisStockHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StockHistoryServiceImpl implements StockHistoryService{

    private final RedisStockHistoryRepository redisStockHistoryRepository;


    @Override
    public Mono<StockHistory> create(StockHistory stockHistory) {
        return redisStockHistoryRepository.save(stockHistory);
    }

    @Override
    public Flux<StockHistory> getAll() {
        return redisStockHistoryRepository.getAll();
    }

    @Override
    public Mono<StockHistory> getOne(String id) {
        return redisStockHistoryRepository.get(id);
    }

    @Override
    public Mono<Long> deleteById(String id) {
        return redisStockHistoryRepository.delete(id);
    }
}
