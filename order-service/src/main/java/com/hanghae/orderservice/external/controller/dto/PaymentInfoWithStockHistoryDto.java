package com.hanghae.orderservice.external.controller.dto;

public record PaymentInfoWithStockHistoryDto(

    Long productId,

    Long userId,

    Integer quantity,

    Integer totalPrice

) {
}
