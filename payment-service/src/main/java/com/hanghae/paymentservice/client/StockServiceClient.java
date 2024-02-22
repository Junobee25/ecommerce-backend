package com.hanghae.paymentservice.client;

import com.hanghae.paymentservice.client.dto.StockHistoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="stock-service")
public interface StockServiceClient {

    @PostMapping("/stock-service/stock/purchase")
    void decreaseStock(@RequestBody StockHistoryDto request);

    @PostMapping("/stock-service/stock/cancel")
    void increaseStock(@RequestBody StockHistoryDto request);
}
