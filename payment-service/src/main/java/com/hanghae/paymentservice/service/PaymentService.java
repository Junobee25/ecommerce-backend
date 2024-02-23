package com.hanghae.paymentservice.service;

import com.hanghae.paymentservice.client.OrdersServiceClient;
import com.hanghae.paymentservice.client.StockServiceClient;
import com.hanghae.paymentservice.client.UserServiceClient;
import com.hanghae.paymentservice.client.dto.OrdersWithPaymentAdapterDto;
import com.hanghae.paymentservice.client.dto.StockWithPaymentAdapterDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrdersServiceClient ordersServiceClient;
    private final UserServiceClient userServiceClient;
    private final StockServiceClient stockServiceClient;

    @Transactional
    public void entryPayment(HttpHeaders headers) {
        //TODO: 주문 정보 입력 후 결제 시작 버튼 -> 재고 수량 감소
        processPayment(headers);
    }

    @Transactional
    public void payment(HttpHeaders headers) {
        //TODO: 20% 이탈 고려하여 Random으로 Redis DB 내 유저 주문결제 정보 삭제
        cancel(headers);
    }

    @Transactional
    public void cancel(HttpHeaders headers) {
        //TODO: 결제 정보 입력(카드사, 계좌 번호, etc..)창 결제 취소 버튼 사용자 의도적 이탈 20%
        if (calculateFailure()) {
            processPayment(headers);
        }
    }

    private void processPayment(HttpHeaders headers) {
        String userEmail = userServiceClient.getUserEmail(headers);
        Long userId = userServiceClient.getUserId(userEmail);

        List<OrdersWithPaymentAdapterDto> orders = ordersServiceClient.getOrdersInfo(userId);
        List<StockWithPaymentAdapterDto> stockHistory = getStockHistory(orders);

        stockHistory.forEach(stockServiceClient::increaseStock);
    }

    private Boolean calculateFailure() {
        int randomValue = new Random().nextInt(100);
        return randomValue < 20;
    }

    private List<StockWithPaymentAdapterDto> getStockHistory(List<OrdersWithPaymentAdapterDto> orders) {
        return orders.stream()
                .map(order -> StockWithPaymentAdapterDto.of(order.productId(), order.quantity()))
                .toList();
    }
}
