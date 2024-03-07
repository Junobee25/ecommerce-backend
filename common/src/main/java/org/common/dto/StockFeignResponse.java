package org.common.dto;

public record StockFeignResponse(
        Long productId,

        Integer quantity) {

    public static StockFeignResponse of(Long productId, Integer quantity) {
        return new StockFeignResponse(productId, quantity);
    }
}
