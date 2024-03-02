package com.hanghae.orderservice.domain.entity;

import com.hanghae.orderservice.domain.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(indexes = {
        @Index(name = "idx_user_id", columnList = "userId"),
        @Index(name = "idx_product_id", columnList = "productId")
})
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

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false, length = 500)
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    protected Orders() {

    }

    private Orders(Long userId, Long productId, Integer quantity, Integer totalPrice, String deliveryAddress, OrderStatus orderStatus) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.deliveryAddress = deliveryAddress;
        this.orderStatus = orderStatus;
    }

    public static Orders of(Long userId, Long productId, Integer quantity, Integer totalPrice, String deliveryAddress, OrderStatus orderStatus) {
        return new Orders(userId, productId, quantity, totalPrice, deliveryAddress, orderStatus);
    }
}
