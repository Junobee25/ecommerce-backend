package com.hanghae.paymentservice.client.dto;

public record StockWithPaymentAdapterDto(

        Long productId,

        Integer quantity
) {
    public static StockWithPaymentAdapterDto of(Long productId, Integer quantity) {
        return new StockWithPaymentAdapterDto(productId, quantity);
    }
}
