package com.hanghae.productservice.service;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ProductLockFacade {

    private final ProductService productService;
    private final RedissonClient redissonClient;

    public void purchase(Long productId, Long quantity) {
        RLock lock = redissonClient.getLock(String.format("purchase:product:%d", productId));
        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);
            if (!available) {
                System.out.println("redisson getLock timeout");
                throw new IllegalArgumentException();
            }
            productService.purchase(productId, quantity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
