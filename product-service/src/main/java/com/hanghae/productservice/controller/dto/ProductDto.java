package com.hanghae.productservice.controller.dto;

import com.hanghae.productservice.domain.constant.ProductType;
import com.hanghae.productservice.domain.entity.Product;

public record ProductDto(

    Long id,

    String name,

    Integer price,

    String description,

    ProductType productType

) {

    public static ProductDto from(Product entity) {
       return new ProductDto(
               entity.getId(),
               entity.getName(),
               entity.getPrice(),
               entity.getDescription(),
               entity.getProductType()
       );
    }
}
