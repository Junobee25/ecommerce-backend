package com.hanghae.orderservice.controller.dto.request;

public record OrdersRequest(

        Long productId,

        Integer quantity,

        String deliveryAddress
) {
}
