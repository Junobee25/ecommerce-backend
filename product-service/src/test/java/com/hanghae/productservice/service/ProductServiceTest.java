//package com.hanghae.productservice.service;
//
//import com.hanghae.productservice.domain.constant.ProductType;
//import com.hanghae.productservice.domain.entity.Product;
//import com.hanghae.productservice.domain.repository.ProductRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//class ProductServiceTest {
//
//
//    @Autowired private ProductRepository productRepository;
//    @Autowired private ProductLockFacade productLockFacade;
//    @Autowired private ProductService productService;
//
//    @Test
//    void 동시에_100명이_신발을_구매한다() throws InterruptedException {
//        Long productId = productRepository.save(Product.of("나이키", 36_000, "예쁜 나이키 신발", ProductType.NOT_RESERVATION, Stock.of(100L)))
//                .getId();
//        ExecutorService executorService = Executors.newFixedThreadPool(100);
//        CountDownLatch countDownLatch = new CountDownLatch(10000);
//
//        for (int i = 0; i < 100; i++) {
//            executorService.submit(() -> {
//                try {
//                    productLockFacade.purchase(productId, 1L);
//                } finally {
//                    countDownLatch.countDown();
//                }
//            });
//        }
//
//        countDownLatch.await();
//        Product actual = productRepository.findById(productId)
//                .orElseThrow();
//
//        assertThat(actual.getStock().getRemain()).isZero();
//    }
//}