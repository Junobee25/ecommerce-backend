package com.hanghae.productservice.controller.dto.request;

public record StockEnrollmentRequest(

        Long productId,

        Long quantity
) {
}
