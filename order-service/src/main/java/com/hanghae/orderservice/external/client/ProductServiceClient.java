package com.hanghae.orderservice.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "product-service")
@RequestMapping("/product-service/products")
public interface ProductServiceClient {

}
