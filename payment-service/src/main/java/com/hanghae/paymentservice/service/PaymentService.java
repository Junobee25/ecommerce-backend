package com.hanghae.paymentservice.service;

import com.hanghae.paymentservice.client.OrdersClient;
import com.hanghae.paymentservice.client.UserServiceClient;
import com.hanghae.paymentservice.controller.dto.PaymentInfoDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrdersClient ordersClient;
    private final UserServiceClient userServiceClient;

    @Transactional
    public void entryPayment(@RequestHeader HttpHeaders headers) {
        //TODO: 결제진입 시 상품 ID, 수량 파라미터를 재고 서비스에 넘겨주고, 재고 서비스에서 동시 요청에 대한 재고 처리를 한다. , 재고량 서비스와 통신 해야 함.
        List<PaymentInfoDto> paymentInfo = ordersClient.getPaymentInfo(userServiceClient.getUserId(userServiceClient.getUserEmail(headers)));

    }

    @Transactional
    public void cancel(@RequestHeader HttpHeaders headers) {
        //TODO: 실시간 재고량 Redis DB에서 유저ID 값으로 조회하여 유저 주문 정보 삭제

    }

    @Transactional
    public void payment(@RequestHeader HttpHeaders headers) {
        //TODO: 20% 이탈 고려하여 Random으로 Redis DB 내 유저 주문결제 정보 삭제

    }
}
