package hanghae.stockservice.external.controller.dto;

public record StockAdapterDto(

        Long productId,

        Integer quantity
) {

    public static StockAdapterDto of(Long productId, Integer quantity) {
        return new StockAdapterDto(productId, quantity);
    }
}
