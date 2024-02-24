package com.hanghae.paymentservice.client;

import com.hanghae.paymentservice.client.dto.OrdersWithPaymentAdapterDto;
import com.hanghae.paymentservice.controller.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrdersServiceClient {

    @GetMapping("/order-service/orders")
    List<OrdersWithPaymentAdapterDto> getOrdersInfo(@RequestParam(value = "userId") Long userId);

    @PostMapping("/order-service/orders/complete")
    Response<Void> completeOrders(@RequestParam(value = "userId") Long userId);

    @PostMapping("/order-service/orders/cancel")
    Response<Void> cancelOrders(@RequestParam(value = "userId") Long userId);

}
