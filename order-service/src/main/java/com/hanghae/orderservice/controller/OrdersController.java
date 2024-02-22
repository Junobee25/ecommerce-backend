package com.hanghae.orderservice.controller;

import com.hanghae.orderservice.controller.dto.OrdersDto;
import com.hanghae.orderservice.controller.dto.request.OrdersRequest;
import com.hanghae.orderservice.controller.dto.response.Response;
import com.hanghae.orderservice.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrdersController {

    private final OrdersService orderService;

    @PostMapping("/order")
    public Response<OrdersDto> order(@RequestBody OrdersRequest request, @RequestHeader HttpHeaders headers) {
        return Response.success(orderService.order(
                request.productId(),
                request.quantity(),
                request.deliveryAddress(),
                headers));
    }

    @DeleteMapping("/order/{orderId}")
    public Response<Void> cancelOrder(@PathVariable(value="orderId") Long orderId, @RequestHeader HttpHeaders headers) {
        orderService.cancelOrder(orderId, headers);
        return Response.success();
    }
}
