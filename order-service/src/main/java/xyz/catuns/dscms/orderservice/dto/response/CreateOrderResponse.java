package xyz.catuns.dscms.orderservice.dto.response;

import xyz.catuns.dscms.orderservice.entity.OrderStatus;

import java.time.Instant;

public record CreateOrderResponse(
        Long orderId,
        String orderNumber,
        OrderStatus status, // use enum
        Double totalAmount,
        Instant createdAt // could use LocalDateTime
) {
}
