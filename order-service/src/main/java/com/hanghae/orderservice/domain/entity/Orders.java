package com.hanghae.orderservice.domain.entity;

import com.hanghae.orderservice.domain.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, length = 500)
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    protected Orders() {

    }

    private Orders(Long userId, Long productId, Integer quantity, String deliveryAddress, OrderStatus orderStatus) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.deliveryAddress = deliveryAddress;
        this.orderStatus = orderStatus;
    }

    public static Orders of(Long userId, Long productId, Integer quantity, String deliveryAddress, OrderStatus orderStatus) {
        return new Orders(userId, productId, quantity, deliveryAddress, orderStatus);
    }
}