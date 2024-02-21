package com.hanghae.orderservice.external.service;

import com.hanghae.orderservice.domain.repository.OrdersRepository;
import com.hanghae.orderservice.external.controller.dto.PaymentInfoWithStockHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExternalService {

    private final OrdersRepository ordersRepository;

    public List<PaymentInfoWithStockHistoryDto> getPaymentInfos(Long userId) {
        return ordersRepository.findByUserId(userId).stream()
                .map(order -> new PaymentInfoWithStockHistoryDto(
                        order.getProductId(),
                        order.getUserId(),
                        order.getQuantity(),
                        order.getTotalPrice()))
                .collect(Collectors.toList());

    }
}
