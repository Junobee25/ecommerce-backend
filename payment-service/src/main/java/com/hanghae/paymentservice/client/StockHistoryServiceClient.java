package com.hanghae.paymentservice.client;

import com.hanghae.paymentservice.client.dto.StockWithPaymentAdapterDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "stock-history-service")
public interface StockHistoryServiceClient {

    @PostMapping("/stock-history-service/stock-history")
    @ResponseStatus(HttpStatus.CREATED)
    void addStockHistory(@RequestBody @Valid StockWithPaymentAdapterDto stockHistoryDto);

}
