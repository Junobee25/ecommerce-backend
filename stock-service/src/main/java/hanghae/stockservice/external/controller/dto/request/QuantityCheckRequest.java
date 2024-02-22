package hanghae.stockservice.external.controller.dto.request;

public record QuantityCheckRequest(
        Long productId,
        Integer orderQuantity
) {
}
