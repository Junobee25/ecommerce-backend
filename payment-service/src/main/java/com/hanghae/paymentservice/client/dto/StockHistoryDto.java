package com.hanghae.paymentservice.client.dto;

public record StockHistoryDto(

        Long productId,

        Integer quantity
) {
    public static StockHistoryDto of(Long productId, Integer quantity) {
        return new StockHistoryDto(productId, quantity);
    }
}
