package hanghae.stockservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RedisStock implements Serializable {

    private Long productId;
    private Integer remain;

    public void verifyRemainAvailability(final Integer quantity) {
        if ((remain - quantity) < 0) {
            throw new IllegalArgumentException();
        }
        remain -= quantity;
    }
}
