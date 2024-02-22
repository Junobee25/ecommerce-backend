package com.hanghae.orderservice.external.client.dto.request;

public record QuantityCheckRequest(
        Long productId,
        Integer quantity
) {

    public static QuantityCheckRequest of(Long productId, Integer quantity) {
        return new QuantityCheckRequest(productId, quantity);
    }
}
