package xyz.catuns.dscms.orderservice.dto.requests;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequest(
        @NotNull
        Long customerId,
        List<OrderLineRequest> lines
) {
}
