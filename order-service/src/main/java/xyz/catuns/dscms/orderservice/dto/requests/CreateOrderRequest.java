package xyz.catuns.dscms.orderservice.dto.requests;

import java.util.List;

public record CreateOrderRequest(
        Long customerId,
        List<OrderLineRequest> lines
) {
}
