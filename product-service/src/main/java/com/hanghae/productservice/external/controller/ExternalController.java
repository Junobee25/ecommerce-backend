package com.hanghae.productservice.external.controller;

import com.hanghae.productservice.external.service.ExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-service")
public class ExternalController {

    private final ExternalService externalService;

    @GetMapping("/products/detail-price")
    public Integer getProductPrice(@RequestParam(value = "productId") Long productId) {
        return externalService.getProductPrice(productId);
    }
}
