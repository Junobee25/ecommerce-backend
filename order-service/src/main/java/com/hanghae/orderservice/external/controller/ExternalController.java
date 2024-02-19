package com.hanghae.orderservice.external.controller;

import com.hanghae.orderservice.external.controller.dto.PaymentInfoDto;
import com.hanghae.orderservice.external.service.ExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class ExternalController {

    private final ExternalService externalService;

    @GetMapping("/orders")
    public List<PaymentInfoDto> getPaymentInfos(@RequestParam(value="userId") Long userId) {
        return externalService.getPaymentInfos(userId);
    }
}
