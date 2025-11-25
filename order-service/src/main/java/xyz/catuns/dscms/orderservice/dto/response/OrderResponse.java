package xyz.catuns.dscms.orderservice.dto.response;

import xyz.catuns.dscms.orderservice.entity.OrderStatus;

public record OrderResponse(
        Long orderLineId,
        Long productId,
        Long quantity,
        Double unitPrice,
        Double lineAmount,
        OrderStatus status
) {
}
