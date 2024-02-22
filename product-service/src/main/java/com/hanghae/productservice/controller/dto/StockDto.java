package com.hanghae.productservice.controller.dto;

public record StockDto(

        Long productId,

        Long quantity
) {

    public static StockDto of(Long productId, Long quantity) {
        return new StockDto(productId, quantity);
    }
}
