package com.hanghae.paymentservice.client;

import com.hanghae.paymentservice.controller.dto.PaymentInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("order-service")
@RequestMapping("/order-service")
public interface OrdersClient {

    @GetMapping("/orders")
    List<PaymentInfoDto> getPaymentInfo(@RequestParam(value="userId") Long userId);

}
