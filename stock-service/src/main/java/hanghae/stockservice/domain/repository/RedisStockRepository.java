package hanghae.stockservice.domain.repository;

import hanghae.stockservice.domain.entity.RedisStock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RedisStockRepository {

    private final RedisTemplate<String, RedisStock> redisTemplate;
    private final static Duration REDIS_STOCK_CACHE_TTL = Duration.ofDays(1);


    public void save(RedisStock redisStock) {
        String key = getKey(redisStock.getProductId());
        log.info("Set RedisStock to Redis {}:{}", key, redisStock);
        redisTemplate.opsForValue().set(key, redisStock, REDIS_STOCK_CACHE_TTL);

    }

    public void decrementStock(Long productId, Integer quantity) {
        String key = getKey(productId);
        RedisStock redisStock = redisTemplate.opsForValue().get(key);

        redisStock.verifyRemainAvailability(quantity);
        redisStock.setRemain(redisStock.getRemain() - quantity);
        redisTemplate.opsForValue().set(key, redisStock);
    }

    public void incrementStock(Long productId, Integer quantity) {
        String key = getKey(productId);
        RedisStock redisStock = redisTemplate.opsForValue().get(key);

        redisStock.setRemain(redisStock.getRemain() + quantity);
        redisTemplate.opsForValue().set(key, redisStock);
    }

    public Optional<RedisStock> findProductStock(Long productId) {
        String key = getKey(productId);
        RedisStock redisStock = redisTemplate.opsForValue().get(key);
        log.info("Get RedisStock from Redis {}:{}", key, redisStock);
        return Optional.ofNullable(redisStock);
    }

    private String getKey(Long productId) {
        return "PRODUCT:" + productId;
    }
}
