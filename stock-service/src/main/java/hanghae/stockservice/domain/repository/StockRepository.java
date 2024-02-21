package hanghae.stockservice.domain.repository;

import hanghae.stockservice.domain.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
