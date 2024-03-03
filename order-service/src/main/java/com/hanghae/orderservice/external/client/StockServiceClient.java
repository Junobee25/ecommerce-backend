package com.hanghae.orderservice.external.client;

import com.hanghae.orderservice.controller.dto.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="stock-service")
public interface StockServiceClient {

    @GetMapping("/stock-service/redis-stock")
    Response<Void> checkOrderQuantityAgainstProduct(@RequestParam Long productId, @RequestParam Integer orderQuantity);
}
