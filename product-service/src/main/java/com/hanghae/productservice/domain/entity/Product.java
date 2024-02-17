package com.hanghae.productservice.domain.entity;

import com.hanghae.productservice.domain.constant.ProductType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    protected Product() {

    }

    private Product(String name, Integer price, String description, ProductType productType) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.productType = productType;
    }

    public static Product of(String name, Integer price, String description, ProductType productType) {
        return new Product(name, price, description, productType);
    }
}


