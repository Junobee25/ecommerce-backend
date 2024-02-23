package com.hanghae.paymentservice.client;

import com.hanghae.paymentservice.client.dto.OrdersWithPaymentAdapterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrdersServiceClient {

    @GetMapping("/order-service/orders")
    List<OrdersWithPaymentAdapterDto> getOrdersInfo(@RequestParam(value = "userId") Long userId);

}
