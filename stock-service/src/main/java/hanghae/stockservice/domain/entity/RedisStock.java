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

    public Integer decrease(Integer quantity) {
        if (remain != null) {
            return remain - quantity;
        }

        return 0;
    }

    public Integer increase(Integer quantity) {
        return remain + quantity;
    }

    public void verifyRemainAvailability(final Integer quantity) {
        if ((remain - quantity) < 0) {
            throw new IllegalArgumentException();
        }
    }
}
