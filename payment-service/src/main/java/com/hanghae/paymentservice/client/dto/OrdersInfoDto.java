package com.hanghae.paymentservice.client.dto;

public record OrdersInfoDto(

        Long productId,

        Long userId,

        Integer quantity,

        Integer totalPrice
) {
}
