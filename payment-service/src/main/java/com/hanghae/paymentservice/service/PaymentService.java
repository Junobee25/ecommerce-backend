package com.hanghae.paymentservice.service;

import com.hanghae.paymentservice.client.OrdersServiceClient;
import com.hanghae.paymentservice.client.StockHistoryServiceClient;
import com.hanghae.paymentservice.client.UserServiceClient;
import com.hanghae.paymentservice.controller.dto.PaymentInfoWithStockHistoryDto;
import com.hanghae.paymentservice.controller.dto.StockHistoryDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrdersServiceClient ordersServiceClient;
    private final UserServiceClient userServiceClient;
    private final StockHistoryServiceClient stockHistoryServiceClient;

    @Transactional
    public void entryPayment(@RequestHeader HttpHeaders headers) {
        List<PaymentInfoWithStockHistoryDto> paymentInfo = ordersServiceClient.getPaymentInfo(userServiceClient.getUserId(userServiceClient.getUserEmail(headers)));
        List<StockHistoryDto> stockHistoryList = getStockHistory(paymentInfo);

        // 재고 사용량 서비스와 통신 : 각 주문에 대해 재고 사용량 DB 업데이트
        stockHistoryList.forEach(stockHistoryServiceClient::addStockHistory);
    }

    @Transactional
    public void cancel(@RequestHeader HttpHeaders headers) {
        //TODO: 실시간 재고량 Redis DB에서 유저ID 값으로 조회하여 유저 주문 정보 삭제

    }

    @Transactional
    public void payment(@RequestHeader HttpHeaders headers) {
        //TODO: 20% 이탈 고려하여 Random으로 Redis DB 내 유저 주문결제 정보 삭제

    }


    private List<StockHistoryDto> getStockHistory(List<PaymentInfoWithStockHistoryDto> paymentInfo) {
        return paymentInfo.stream()
                .map(info -> StockHistoryDto.of(info.productId(), info.userId(), info.quantity()))
                .toList();
    }
}
