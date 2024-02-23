package com.hanghae.paymentservice.controller;

import com.hanghae.paymentservice.controller.response.Response;
import com.hanghae.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment-service")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/enroll-payment")
    public Response<Void> entryPayment(@RequestHeader HttpHeaders headers) {
        paymentService.entryPayment(headers);
        return Response.success();
    }

    @PostMapping("/payment")
    public Response<Void> payment(@RequestHeader HttpHeaders headers) {
        paymentService.payment(headers);
        return Response.success();
    }

    @PostMapping("/cancel")
    public Response<Void> cancel(@RequestHeader HttpHeaders headers) {
        paymentService.cancel(headers);
        return Response.success();
    }
}
