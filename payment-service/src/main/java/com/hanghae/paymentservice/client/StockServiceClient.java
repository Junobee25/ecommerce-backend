package com.hanghae.paymentservice.client;

import org.common.dto.StockFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="stock-service")
public interface StockServiceClient {

    @PostMapping("/stock-service/redis-stock/purchase")
    void decreaseStock(@RequestBody StockFeignResponse request);

    @PostMapping("/stock-service/redis-stock/cancel")
    void increaseStock(@RequestBody StockFeignResponse request);
}
