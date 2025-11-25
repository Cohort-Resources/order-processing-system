package xyz.catuns.dscms.orderservice.dto.requests;

import xyz.catuns.dscms.orderservice.entity.OrderStatus;

public record UpdateOrderStatusRequest(
        OrderStatus status,
        String reason
) {
}
