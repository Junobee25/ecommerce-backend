package hanghae.stockservice.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long remain;

    private Stock(Long productId, Long remain) {
        this.productId = productId;
        this.remain = remain;
    }

    public static Stock of(Long productId, Long remain) {
        return new Stock(productId, remain);
    }

    public void purchase(final Long quantity) {
        decrease(quantity);
    }

    public void decrease(final Long quantity) {
        if ((remain - quantity) < 0) {
            throw new IllegalArgumentException();
        }
        remain -= quantity;
    }
}
