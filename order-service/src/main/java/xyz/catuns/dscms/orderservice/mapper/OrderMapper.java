package xyz.catuns.dscms.orderservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import xyz.catuns.dscms.orderservice.dto.requests.CreateOrderRequest;
import xyz.catuns.dscms.orderservice.dto.response.CreateOrderResponse;
import xyz.catuns.dscms.orderservice.dto.response.OrderResponse;
import xyz.catuns.dscms.orderservice.entity.Order;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        uses = {OrderLineMapper.class},
        componentModel = SPRING,
        injectionStrategy = CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    Order toEntity(CreateOrderRequest request);

    CreateOrderResponse toCreateOrderResponse(Order order);

    OrderResponse toResponse(Order order);

    Page<OrderResponse> toResponsePage(Page<Order> orderPage);
}
