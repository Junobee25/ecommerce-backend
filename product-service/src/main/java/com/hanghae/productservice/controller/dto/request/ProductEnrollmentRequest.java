package com.hanghae.productservice.controller.dto.request;

import com.hanghae.productservice.domain.constant.ProductType;

public record ProductEnrollmentRequest(

        String name,

        Integer price,

        String description,

        ProductType productType,

        Integer quantity

) {
}
