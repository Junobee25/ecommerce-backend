package hanghae.stockservice.external.controller.dto.request;

public record PurchaseRequest(
        Long productId,

        Integer quantity

) {
}
