package com.hanghae.orderservice.controller.dto.request;

public record OrdersRequestDto(

        Long productId,

        Integer quantity,

        Integer totalPrice,

        String deliveryAddress
) {
}
