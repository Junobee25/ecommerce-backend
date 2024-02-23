package com.hanghae.paymentservice.client.dto;

public record OrdersWithPaymentAdapterDto(

        Long productId,

        Long userId,

        Integer quantity,

        Integer totalPrice
) {
}
