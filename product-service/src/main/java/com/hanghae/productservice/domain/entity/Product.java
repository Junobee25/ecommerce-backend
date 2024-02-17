package com.hanghae.productservice.domain.entity;

import com.hanghae.productservice.domain.constant.ProductType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    private Product(String name, Integer price, String description, ProductType productType, Stock stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.productType = productType;
        this.stock = stock;
    }

    public static Product of(String name, Integer price, String description, ProductType productType, Stock stock) {
        return new Product(name, price, description, productType, stock);
    }

    public void purchase(final long quantity) {
        stock.decrease(quantity);
    }
}


