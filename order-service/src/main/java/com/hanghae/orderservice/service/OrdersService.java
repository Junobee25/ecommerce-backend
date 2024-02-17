package com.hanghae.orderservice.service;

import com.hanghae.orderservice.controller.dto.OrdersDto;
import com.hanghae.orderservice.controller.dto.request.OrdersRequestDto;
import com.hanghae.orderservice.domain.constant.ErrorCode;
import com.hanghae.orderservice.domain.constant.OrderStatus;
import com.hanghae.orderservice.domain.entity.Orders;
import com.hanghae.orderservice.domain.repository.OrdersRepository;
import com.hanghae.orderservice.exception.OrdersServiceApplicationException;
import com.hanghae.orderservice.external.client.ProductServiceClient;
import com.hanghae.orderservice.external.client.UserServiceClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository orderRepository;
    private final UserServiceClient userServiceClient;

    @Transactional
    public OrdersDto order(OrdersRequestDto request, HttpHeaders headers) {
        //TODO : OrderStatus == COMPLETE => 결제 진행
        return OrdersDto.from(orderRepository.save(Orders.of(
                userServiceClient.getUserId(userServiceClient.getUserEmail(headers)),
                request.productId(),
                request.quantity(),
                request.deliveryAddress(),
                OrderStatus.COMPLETE)));
    }

    @Transactional
    public void cancelOrder(Long orderId, HttpHeaders headers) {
        //TODO: 주문 취소 Flow (주문입력 페이지 -> 결제 하시겠습니까 페이지 => 결제 진행(결제 서비스 이동) or 주문 취소)
        Long userId = userServiceClient.getUserId(userServiceClient.getUserEmail(headers));
        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrdersServiceApplicationException(ErrorCode.ORDER_NOT_FOUND));

        if (!Objects.equals(orders.getUserId(), userId)) {
            throw new OrdersServiceApplicationException(ErrorCode.INVALID_PERMISSION);
        }

        orderRepository.delete(orders);
    }
}
