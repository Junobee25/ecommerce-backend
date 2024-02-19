package com.hanghae.paymentservice.client;

import com.hanghae.paymentservice.controller.dto.PaymentInfoWithStockHistoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrdersServiceClient {

    @GetMapping("/order-service/orders")
    List<PaymentInfoWithStockHistoryDto> getPaymentInfo(@RequestParam(value = "userId") Long userId);

}
