package hanghae.stockservice.external.controller.dto;

public record StockDto(

        Long productId,

        Integer quantity
) {

    public static StockDto of(Long productId, Integer quantity) {
        return new StockDto(productId, quantity);
    }
}
