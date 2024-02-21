package com.hanghae.paymentservice.controller.dto;

public record PaymentInfoWithStockHistoryDto(

        Long productId,

        Long userId,

        Integer quantity,

        Integer totalPrice
) {
}
