package hanghae.stockservice.external.controller.dto.request;

public record CancelRequest(
        Long productId,

        Integer quantity
) {
}
