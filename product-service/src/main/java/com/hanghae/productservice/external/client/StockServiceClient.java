package com.hanghae.productservice.external.client;

import com.hanghae.productservice.external.client.dto.StockWithProductAdapterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "stock-service")
public interface StockServiceClient {

    //TODO: MSA 간 트랜잭션 관리 필요,,!
    @PostMapping("/stock-service/stock/enroll-stock")
    void enrollStock(@RequestBody StockWithProductAdapterDto request);

}
