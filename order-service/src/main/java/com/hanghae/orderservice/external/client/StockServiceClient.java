package com.hanghae.orderservice.external.client;

import com.hanghae.orderservice.controller.dto.response.Response;
import com.hanghae.orderservice.external.client.dto.request.QuantityCheckRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="stock-service")
public interface StockServiceClient {

    @GetMapping("/stock-service/stock")
    Response<Void> checkOrderQuantityAgainstProduct(@RequestBody QuantityCheckRequest request);
}
