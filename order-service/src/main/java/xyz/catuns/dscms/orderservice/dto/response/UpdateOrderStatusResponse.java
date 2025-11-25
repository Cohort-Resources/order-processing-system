package xyz.catuns.dscms.orderservice.dto.response;

import xyz.catuns.dscms.orderservice.entity.OrderStatus;

import java.time.Instant;

public record UpdateOrderStatusResponse(
        Long orderId,
        OrderStatus status,
        Instant updatedAt
) {
}
