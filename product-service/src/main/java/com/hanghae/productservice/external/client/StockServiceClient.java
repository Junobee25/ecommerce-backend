package com.hanghae.productservice.external.client;

import org.common.dto.StockFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "stock-service")
public interface StockServiceClient {

    //TODO: MSA 간 트랜잭션 관리 필요,,!
    @PostMapping("/stock-service/redis-stock/enroll-stock")
    void enrollStock(@RequestBody StockFeignResponse request);

}
