package com.hanghae.paymentservice.service;

import com.hanghae.paymentservice.client.OrdersServiceClient;
import com.hanghae.paymentservice.client.StockServiceClient;
import com.hanghae.paymentservice.client.UserServiceClient;
import com.hanghae.paymentservice.client.dto.OrdersWithPaymentAdapterDto;
import com.hanghae.paymentservice.client.dto.StockWithPaymentAdapterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrdersServiceClient ordersServiceClient;
    private final UserServiceClient userServiceClient;
    private final StockServiceClient stockServiceClient;

    @Transactional
    public void entryPayment(HttpHeaders headers) {
        entryPaymentProcess(headers);
    }

    @Transactional
    public void payment(HttpHeaders headers) {
        completePaymentProcess(headers);
    }

    @Transactional
    public void cancel(HttpHeaders headers) {
        cancelProcessPayment(headers);
    }

    private void entryPaymentProcess(HttpHeaders headers) {
        List<OrdersWithPaymentAdapterDto> orders = ordersServiceClient.getOrdersInfo(getUserInfo(headers));
        List<StockWithPaymentAdapterDto> stockHistory = getStockHistory(orders);

        stockHistory.forEach(stockServiceClient::decreaseStock);
    }

    private void completePaymentProcess(HttpHeaders headers) {
        List<OrdersWithPaymentAdapterDto> orders = ordersServiceClient.getOrdersInfo(getUserInfo(headers));
        List<StockWithPaymentAdapterDto> stockHistory = getStockHistory(orders);

        if (calculateFailure()) {
            cancelOrders(orders);
            stockHistory.forEach(stockServiceClient::increaseStock);
            return;
        }

        completeOrders(orders);
    }

    private void cancelProcessPayment(HttpHeaders headers) {
        List<OrdersWithPaymentAdapterDto> orders = ordersServiceClient.getOrdersInfo(getUserInfo(headers));
        List<StockWithPaymentAdapterDto> stockHistory = getStockHistory(orders);

        if (calculateFailure()) {
            cancelOrders(orders);
            stockHistory.forEach(stockServiceClient::increaseStock);
        }
    }

    private void cancelOrders(List<OrdersWithPaymentAdapterDto> orders) {
        orders.stream()
                .map(OrdersWithPaymentAdapterDto::userId)
                .toList()
                .forEach(ordersServiceClient::cancelOrders);
    }

    private void completeOrders(List<OrdersWithPaymentAdapterDto> orders) {
        orders.stream()
                .map(OrdersWithPaymentAdapterDto::userId)
                .toList()
                .forEach(ordersServiceClient::completeOrders);
    }

    private Long getUserInfo(HttpHeaders headers) {
        return userServiceClient.getUserInfo(headers);
    }

    private List<StockWithPaymentAdapterDto> getStockHistory(List<OrdersWithPaymentAdapterDto> orders) {
        return orders.stream()
                .map(order -> StockWithPaymentAdapterDto.of(order.productId(), order.quantity()))
                .toList();
    }

    private Boolean calculateFailure() {
        int randomValue = new Random().nextInt(100);
        return randomValue < 20;
    }
}
