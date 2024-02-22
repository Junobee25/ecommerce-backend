package hanghae.stockservice.external.service;

import hanghae.stockservice.domain.entity.Stock;
import hanghae.stockservice.domain.repository.StockRepository;
import hanghae.stockservice.external.controller.dto.StockDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    @Transactional
    public void checkOrderQuantityAgainstProduct(Long productId, Long orderQuantity) {
        Stock stock = stockRepository.findByProductId(productId)
                .orElseThrow(IllegalArgumentException::new);

        if (stock.getRemain() <= orderQuantity) {
            throw new IllegalArgumentException();
        }
    }

    @Transactional
    public void enrollStock(StockDto stockDto) {
        stockRepository.save(Stock.of(stockDto.productId(), stockDto.quantity()));
    }

    @Transactional
    public void purchase(Long productId, Long quantity) {
        Stock stock = stockRepository.findByProductId(productId)
                .orElseThrow(IllegalArgumentException::new);
        stock.purchase(quantity);
    }

    @Transactional
    public void cancel(Long productId, Long quantity) {
        Stock stock = stockRepository.findByProductId(productId)
                .orElseThrow(IllegalArgumentException::new);
        stock.cancel(quantity);
    }
}
