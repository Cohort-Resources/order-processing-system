package xyz.catuns.dscms.orderservice.dto.requests;

public record OrderLineRequest(
        Long productId,
        Long quantity
) {
}
