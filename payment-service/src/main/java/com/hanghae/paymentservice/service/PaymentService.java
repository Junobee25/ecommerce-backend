package com.hanghae.paymentservice.service;

import com.hanghae.paymentservice.client.OrdersServiceClient;
import com.hanghae.paymentservice.client.StockServiceClient;
import com.hanghae.paymentservice.client.UserServiceClient;
import com.hanghae.paymentservice.controller.dto.OrdersInfoDto;
import com.hanghae.paymentservice.controller.dto.StockHistoryDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrdersServiceClient ordersServiceClient;
    private final UserServiceClient userServiceClient;
    private final StockServiceClient stockServiceClient;

    @Transactional
    public void entryPayment(HttpHeaders headers) {
        String userEmail = userServiceClient.getUserEmail(headers);
        Long userId = userServiceClient.getUserId(userEmail);

        List<OrdersInfoDto> orders = ordersServiceClient.getOrdersInfo(userId);
        List<StockHistoryDto> stockHistoryList = getStockHistory(orders);

        stockHistoryList.forEach(stockServiceClient::decreaseStock);

    }

    @Transactional
    public void cancel(@RequestHeader HttpHeaders headers) {
        //TODO: 실시간 재고량 Redis DB에서 유저ID 값으로 조회하여 유저 주문 정보 삭제

    }

    @Transactional
    public void payment(@RequestHeader HttpHeaders headers) {
        //TODO: 20% 이탈 고려하여 Random으로 Redis DB 내 유저 주문결제 정보 삭제

    }


    private List<StockHistoryDto> getStockHistory(List<OrdersInfoDto> paymentInfo) {
        return paymentInfo.stream()
                .map(info -> StockHistoryDto.of(info.productId(), info.quantity()))
                .toList();
    }
}
