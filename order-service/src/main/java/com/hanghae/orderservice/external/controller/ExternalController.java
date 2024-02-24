package com.hanghae.orderservice.external.controller;

import com.hanghae.orderservice.controller.dto.response.Response;
import com.hanghae.orderservice.external.controller.dto.OrdersWithPaymentAdapterDto;
import com.hanghae.orderservice.external.service.ExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class ExternalController {

    private final ExternalService externalService;

    @GetMapping("/orders")
    public List<OrdersWithPaymentAdapterDto> getPaymentInfos(@RequestParam(value="userId") Long userId) {
        return externalService.getPaymentInfos(userId);
    }

    @PostMapping("/orders/complete")
    public Response<Void> completeOrders(@RequestParam(value = "userId") Long userId) {
        externalService.completeOrders(userId);
        return Response.success();
    }

    @PostMapping("/orders/cancel")
    public Response<Void> cancelOrders(@RequestParam(value = "userId") Long userId) {
        externalService.cancelOrders(userId);
        return Response.success();
    }
}
