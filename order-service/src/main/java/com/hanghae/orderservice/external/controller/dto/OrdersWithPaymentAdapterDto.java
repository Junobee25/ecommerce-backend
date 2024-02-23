package com.hanghae.orderservice.external.controller.dto;

public record OrdersWithPaymentAdapterDto(

    Long productId,

    Long userId,

    Integer quantity,

    Integer totalPrice

) {
}
