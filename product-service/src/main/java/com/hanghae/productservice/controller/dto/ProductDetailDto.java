package com.hanghae.productservice.controller.dto;

import com.hanghae.productservice.domain.entity.Product;

public record ProductDetailDto(
        String name,
        Integer price,
        String description
) {

    public static ProductDetailDto from(Product entity) {
        return new ProductDetailDto(
                entity.getName(),
                entity.getPrice(),
                entity.getDescription()
        );
    }
}
