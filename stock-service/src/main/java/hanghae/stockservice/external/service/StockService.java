package hanghae.stockservice.external.service;

import hanghae.stockservice.domain.entity.Stock;
import hanghae.stockservice.domain.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    @Transactional
    public void enrollStock(Long productId, Long quantity) {
        stockRepository.save(Stock.of(productId, quantity));
    }

    @Transactional
    public void purchase(Long stockId, Long quantity) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(IllegalArgumentException::new);
        stock.purchase(quantity);
    }
}
