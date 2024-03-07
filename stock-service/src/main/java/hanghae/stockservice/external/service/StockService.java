package hanghae.stockservice.external.service;

import hanghae.stockservice.domain.entity.Stock;
import hanghae.stockservice.domain.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.common.dto.StockFeignResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    @Transactional(readOnly = true)
    public void checkOrderQuantityAgainstProduct(Long productId, Integer orderQuantity) {
        Stock stock = stockRepository.findByProductId(productId)
                .orElseThrow(IllegalArgumentException::new);

        if (stock.getRemain() < orderQuantity) {
            throw new IllegalArgumentException();
        }
    }

    @Transactional
    public void enrollStock(StockFeignResponse stockAdapterDto) {
        stockRepository.save(Stock.of(stockAdapterDto.productId(), stockAdapterDto.quantity()));
    }

    @Transactional
    public void purchase(Long productId, Integer quantity) {
        Stock stock = stockRepository.findByIdWithPessimisticLock(productId);
        stock.purchase(quantity);
    }

    @Transactional
    public void cancel(Long productId, Integer quantity) {
        Stock stock = stockRepository.findByProductId(productId)
                .orElseThrow(IllegalArgumentException::new);
        stock.cancel(quantity);
    }
}
