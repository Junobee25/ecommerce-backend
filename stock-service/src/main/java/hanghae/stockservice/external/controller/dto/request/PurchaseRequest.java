package hanghae.stockservice.external.controller.dto.request;

public record PurchaseRequest(
        Long productId,

        Long quantity

) {
}
