package com.hanghae.stockhistoryservice.controller;

import com.hanghae.stockhistoryservice.domain.entity.StockHistory;
import com.hanghae.stockhistoryservice.service.StockHistoryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stock-history-service")
@RequiredArgsConstructor
public class StockHistoryController {

    private final StockHistoryServiceImpl stockHistoryService;

    @PostMapping("/stock-history")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<StockHistory> addStockHistory(@RequestBody @Valid StockHistory stockHistory) {
        return stockHistoryService.create(stockHistory);
    }

    @GetMapping("/stock-history")
    public Flux<StockHistory> getAllDessert() {
        return stockHistoryService.getAll();
    }

    @GetMapping("/stock-history/{userId}")
    public Mono<StockHistory> getDessert(@PathVariable Long userId) {
        return stockHistoryService.getOne(String.valueOf(userId));
    }

    @DeleteMapping("/stock-history/{userId}")
    public Mono<Long> deleteDessert(@PathVariable Long userId) {
        return stockHistoryService.deleteById(String.valueOf(userId));
    }
}
