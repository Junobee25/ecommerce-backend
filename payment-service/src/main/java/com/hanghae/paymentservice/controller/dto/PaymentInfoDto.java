package com.hanghae.paymentservice.controller.dto;

public record PaymentInfoDto(

        Long productId,

        Long userId,

        Integer quantity,

        Integer totalPrice
) {
}
