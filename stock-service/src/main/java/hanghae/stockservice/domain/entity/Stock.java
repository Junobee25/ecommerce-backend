package hanghae.stockservice.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(indexes = {
        @Index(name = "idx_product_id", columnList = "productId")
})
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Integer remain;

    private Stock(Long productId, Integer remain) {
        this.productId = productId;
        this.remain = remain;
    }

    public static Stock of(Long productId, Integer remain) {
        return new Stock(productId, remain);
    }

    public void purchase(final Integer quantity) {
        decrease(quantity);
    }

    public void cancel(final Integer quantity) {increase(quantity); }

    public void decrease(final Integer quantity) {
        if ((remain - quantity) < 0) {
            throw new IllegalArgumentException();
        }
        remain -= quantity;
    }

    public void increase(final Integer quantity) {
        remain += quantity;
    }
}
