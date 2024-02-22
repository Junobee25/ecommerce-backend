package hanghae.stockservice.external.controller.dto.request;

public record QuantityCheckRequest(
        Long productId,
        Long orderQuantity
) {
}
