package com.hanghae.productservice.external.client.dto;

public record StockWithProductAdapterDto(

        Long productId,

        Long quantity
) {

    public static StockWithProductAdapterDto of(Long productId, Long quantity) {
        return new StockWithProductAdapterDto(productId, quantity);
    }
}
