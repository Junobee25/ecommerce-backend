package com.hanghae.paymentservice.controller.dto;

public record OrdersInfoDto(

        Long productId,

        Long userId,

        Integer quantity,

        Integer totalPrice
) {
}
