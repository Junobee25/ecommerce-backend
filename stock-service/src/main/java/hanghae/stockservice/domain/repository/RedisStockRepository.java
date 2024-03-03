package hanghae.stockservice.domain.repository;

import hanghae.stockservice.domain.entity.RedisStock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RedisStockRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final static Duration REDIS_STOCK_CACHE_TTL = Duration.ofDays(1);


    public void save(RedisStock redisStock) {
        String key = getKey(redisStock.getProductId());
        log.info("Set RedisStock to Redis {}:{}", key, redisStock);
        redisTemplate.opsForValue().set(key, redisStock.getRemain(), REDIS_STOCK_CACHE_TTL);

    }

    public void decrementStock(Long productId, Integer quantity) {
        String key = getKey(productId);
        redisTemplate.opsForValue().decrement(key, quantity);
    }

    public void incrementStock(Long productId, Integer quantity) {
        String key = getKey(productId);
        redisTemplate.opsForValue().increment(key, quantity);
    }

    public Optional<Object> findProductStock(Long productId) {
        String key = getKey(productId);
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

    private String getKey(Long productId) {
        return "PRODUCT:" + productId;
    }
}
