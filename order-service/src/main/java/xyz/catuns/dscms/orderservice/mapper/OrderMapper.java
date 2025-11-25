package xyz.catuns.dscms.orderservice.mapper;

import xyz.catuns.dscms.orderservice.dto.requests.CreateOrderRequest;
import xyz.catuns.dscms.orderservice.dto.response.CreateOrderResponse;
import xyz.catuns.dscms.orderservice.entity.Order;

public interface OrderMapper {

    Order toEntity(CreateOrderRequest request);

    CreateOrderResponse toCreateOrderResponse(Order order);
}
