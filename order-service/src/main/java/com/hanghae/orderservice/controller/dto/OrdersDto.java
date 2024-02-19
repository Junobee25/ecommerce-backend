package com.hanghae.orderservice.controller.dto;

import com.hanghae.orderservice.domain.constant.OrderStatus;
import com.hanghae.orderservice.domain.entity.Orders;

public record OrdersDto(

    Long id,

    Long userId,

    Long productId,

    Integer quantity,

    Integer totalPrice,

    String deliveryAddress,

    OrderStatus orderStatus

) {
    public static OrdersDto from(Orders entity) {
        return new OrdersDto(
                entity.getId(),
                entity.getUserId(),
                entity.getProductId(),
                entity.getQuantity(),
                entity.getTotalPrice(),
                entity.getDeliveryAddress(),
                entity.getOrderStatus()
        );
    }
}
