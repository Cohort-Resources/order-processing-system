package xyz.catuns.dscms.orderservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.catuns.dscms.orderservice.dto.requests.CreateOrderRequest;
import xyz.catuns.dscms.orderservice.dto.requests.UpdateOrderStatusRequest;
import xyz.catuns.dscms.orderservice.dto.response.CreateOrderResponse;
import xyz.catuns.dscms.orderservice.dto.response.OrderResponse;
import xyz.catuns.dscms.orderservice.dto.response.UpdateOrderStatusResponse;
import xyz.catuns.dscms.orderservice.entity.OrderStatus;

public interface OrderService {

    CreateOrderResponse createOrder(CreateOrderRequest request);

    OrderResponse getOrders(Long orderId);

    Page<OrderResponse> getAllOrders(Pageable pageable, Long customerId, OrderStatus status);

    UpdateOrderStatusResponse updateOrderStatus(Long id, UpdateOrderStatusRequest request);
}
