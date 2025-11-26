package xyz.catuns.dscms.orderservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.catuns.dscms.orderservice.dto.requests.CreateOrderRequest;
import xyz.catuns.dscms.orderservice.dto.requests.UpdateOrderStatusRequest;
import xyz.catuns.dscms.orderservice.dto.response.CreateOrderResponse;
import xyz.catuns.dscms.orderservice.dto.response.OrderResponse;
import xyz.catuns.dscms.orderservice.dto.response.UpdateOrderStatusResponse;
import xyz.catuns.dscms.orderservice.entity.OrderStatus;
import xyz.catuns.dscms.orderservice.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(
            @Valid @RequestBody CreateOrderRequest request,
            @RequestHeader String bearerToken
    ) {
        // todo: validate bearerToken
        CreateOrderResponse response = orderService.createOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{orderId}")
    @Operation(
            summary = "Get Orders",
            description = "REST API to Get Orders")
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK")
    public ResponseEntity<OrderResponse> getOrders(
            @PathVariable("orderId") Long orderId
    ){
        OrderResponse response = orderService.getOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("")
    @Operation(
            summary = "Get all orders",
            description = "REST API to Get all orders")
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK")
    public ResponseEntity<Page<OrderResponse>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(required = false) Long customerId
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderResponse> response = orderService.getAllOrders(pageable, customerId, status);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}/status")
    @Operation(
            summary = "Update Order status",
            description = "REST API to Update Order status")
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK")
    public ResponseEntity<UpdateOrderStatusResponse> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody UpdateOrderStatusRequest request
    ){
        UpdateOrderStatusResponse response = orderService.updateOrderStatus(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    
}
