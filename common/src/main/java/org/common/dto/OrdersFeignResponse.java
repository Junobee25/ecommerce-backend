package org.common.dto;

public record OrdersFeignResponse(
        Long productId,

        Long userId,

        Integer quantity,

        Integer totalPrice
) {
}
