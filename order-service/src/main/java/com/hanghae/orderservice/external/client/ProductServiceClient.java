package com.hanghae.orderservice.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @GetMapping("/product-service/products/detail-price")
    Integer getProductPrice(@RequestParam(value = "productId") Long productId);
}
