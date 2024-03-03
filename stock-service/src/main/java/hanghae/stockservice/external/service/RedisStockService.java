package hanghae.stockservice.external.service;

import hanghae.stockservice.domain.entity.RedisStock;
import hanghae.stockservice.domain.repository.RedisStockRepository;
import hanghae.stockservice.external.controller.dto.StockAdapterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisStockService {

    private final RedisStockRepository redisStockRepository;

//    @Transactional(readOnly = true)
//    public void checkOrderQuantityAgainstProduct(Long productId, Integer quantity) {
//        RedisStock redisStock = redisStockRepository.findProductStock(productId)
//                .orElseThrow(IllegalArgumentException::new);
//
//        if (redisStock.getRemain() < quantity) {
//            throw new IllegalArgumentException();
//        }
//    }

    @Transactional
    public void enrollStock(StockAdapterDto stockAdapterDto) {
        redisStockRepository.save(new RedisStock(stockAdapterDto.productId(), stockAdapterDto.quantity()));
    }


    public void purchase(StockAdapterDto stockAdapterDto) {
        redisStockRepository.decrementStock(stockAdapterDto.productId(), stockAdapterDto.quantity());
    }

    @Transactional
    public void cancel(StockAdapterDto stockAdapterDto) {
        redisStockRepository.incrementStock(stockAdapterDto.productId(), stockAdapterDto.quantity());
    }

    @Transactional
    public Optional<Object> findProductStock(Long productId) {
        return redisStockRepository.findProductStock(productId);
    }
}
