package com.hanghae.paymentservice.controller.dto;

public record StockHistoryDto(

        Long productId,

        Long userId,

        Integer quantity
) {
    public static StockHistoryDto of(Long productId, Long userId, Integer quantity) {
        return new StockHistoryDto(productId, userId, quantity);
    }
}
