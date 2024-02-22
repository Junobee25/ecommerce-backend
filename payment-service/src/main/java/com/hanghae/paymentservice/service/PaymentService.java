package com.hanghae.paymentservice.service;

import com.hanghae.paymentservice.client.OrdersServiceClient;
import com.hanghae.paymentservice.client.StockServiceClient;
import com.hanghae.paymentservice.client.UserServiceClient;
import com.hanghae.paymentservice.client.dto.OrdersInfoDto;
import com.hanghae.paymentservice.client.dto.StockHistoryDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

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
        String userEmail = userServiceClient.getUserEmail(headers);
        Long userId = userServiceClient.getUserId(userEmail);

        List<OrdersInfoDto> orders = ordersServiceClient.getOrdersInfo(userId);
        List<StockHistoryDto> stockHistoryList = getStockHistory(orders);

        stockHistoryList.forEach(stockServiceClient::decreaseStock);

    }

    @Transactional
    public void cancel(HttpHeaders headers) {
        //TODO: 결제 정보 입력(카드사, 계좌 번호, etc..)창 결제 취소 버튼 사용자 의도적 이탈 20%
        if (calculateFailure()) {
            String userEmail = userServiceClient.getUserEmail(headers);
            Long userId = userServiceClient.getUserId(userEmail);

            List<OrdersInfoDto> orders = ordersServiceClient.getOrdersInfo(userId);
            List<StockHistoryDto> stockHistoryList = getStockHistory(orders);

            stockHistoryList.forEach(stockServiceClient::increaseStock);
        }
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

    private Boolean calculateFailure() {
        int randomValue = new Random().nextInt(100);
        return randomValue < 20;
    }
}
