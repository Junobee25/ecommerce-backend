package hanghae.stockservice.external.service;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class StockLockFacade {

    private final StockService stockService;
    private final RedissonClient redissonClient;

    public void purchase(Long productId, Long quantity) {
        RLock lock = redissonClient.getLock(String.format("purchase:product:%d", productId));
        try {
            boolean available = lock.tryLock(40, 1, TimeUnit.SECONDS);
            if (!available) {
                System.out.println("redisson getLock timeout");
                throw new IllegalArgumentException();
            }
            stockService.purchase(productId, quantity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void cancel(Long productId, Long quantity) {
        RLock lock = redissonClient.getLock(String.format("cancel:product:%d", productId));
        try {
            boolean available = lock.tryLock(40, 1, TimeUnit.SECONDS);
            if (!available) {
                System.out.println("redisson getLock timeout");
                throw new IllegalArgumentException();
            }
            stockService.purchase(productId, quantity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
