package hanghae.stockservice.domain.repository;

import hanghae.stockservice.domain.entity.Stock;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProductId(Long product);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Stock s where s.productId = :productId")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="5000")})
    Stock findByIdWithPessimisticLock(Long productId);
}
