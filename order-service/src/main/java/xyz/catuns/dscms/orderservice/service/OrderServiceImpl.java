package xyz.catuns.dscms.orderservice.service;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.catuns.dscms.orderservice.dto.requests.CreateOrderRequest;
import xyz.catuns.dscms.orderservice.dto.requests.UpdateOrderStatusRequest;
import xyz.catuns.dscms.orderservice.dto.response.CreateOrderResponse;
import xyz.catuns.dscms.orderservice.dto.response.OrderResponse;
import xyz.catuns.dscms.orderservice.dto.response.UpdateOrderStatusResponse;
import xyz.catuns.dscms.orderservice.entity.OrderStatus;

import java.time.Instant;

@Service
@Profile("!dev")
public class OrderServiceImpl implements OrderService {


    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        return new CreateOrderResponse(9001L,"ORD-2025-00001", OrderStatus.PENDING, 1234.50, Instant.now());
    }

    @Override
    public OrderResponse getOrders(Long orderId) {
        return null;
    }

    @Override
    public Page<OrderResponse> getAllOrders(Pageable pageable, Long customerId, OrderStatus status) {
        return null;
    }

    @Override
    public UpdateOrderStatusResponse updateOrderStatus(Long id, UpdateOrderStatusRequest request) {
        return null;
    }
}
