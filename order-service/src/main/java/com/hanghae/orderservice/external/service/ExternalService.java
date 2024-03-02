package com.hanghae.orderservice.external.service;

import com.hanghae.orderservice.domain.constant.OrderStatus;
import com.hanghae.orderservice.domain.repository.OrdersRepository;
import com.hanghae.orderservice.external.controller.dto.OrdersWithPaymentAdapterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExternalService {

    private final OrdersRepository ordersRepository;

    @Transactional(readOnly = true)
    public List<OrdersWithPaymentAdapterDto> getPaymentInfos(Long userId) {
        return ordersRepository.findByUserId(userId).stream()
                .map(order -> new OrdersWithPaymentAdapterDto(
                        order.getProductId(),
                        order.getUserId(),
                        order.getQuantity(),
                        order.getTotalPrice()))
                .collect(Collectors.toList());

    }

    @Transactional
    public void completeOrders(Long userId) {
        ordersRepository.findByUserId(userId)
                .forEach(order -> {
                    order.setOrderStatus(OrderStatus.COMPLETE);
                    ordersRepository.save(order);
                });
    }

    @Transactional
    public void cancelOrders(Long userId) {
        ordersRepository.findByUserId(userId)
                .forEach(order -> {
                    order.setOrderStatus(OrderStatus.CANCEL);
                    ordersRepository.save(order);
                });
    }
}
