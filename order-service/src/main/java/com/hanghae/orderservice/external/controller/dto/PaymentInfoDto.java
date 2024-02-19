package com.hanghae.orderservice.external.controller.dto;

public record PaymentInfoDto(

    Long productId,

    Long userId,

    Integer quantity,

    Integer totalPrice

) {
}
