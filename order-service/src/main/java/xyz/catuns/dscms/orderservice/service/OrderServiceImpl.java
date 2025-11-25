package xyz.catuns.dscms.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.catuns.dscms.orderservice.clients.CustomerServiceClient;
import xyz.catuns.dscms.orderservice.clients.ProductServiceClient;
import xyz.catuns.dscms.orderservice.dto.CustomerDto;
import xyz.catuns.dscms.orderservice.dto.ProductDto;
import xyz.catuns.dscms.orderservice.dto.requests.CreateOrderRequest;
import xyz.catuns.dscms.orderservice.dto.requests.OrderLineRequest;
import xyz.catuns.dscms.orderservice.dto.requests.UpdateOrderStatusRequest;
import xyz.catuns.dscms.orderservice.dto.response.CreateOrderResponse;
import xyz.catuns.dscms.orderservice.dto.response.OrderResponse;
import xyz.catuns.dscms.orderservice.dto.response.UpdateOrderStatusResponse;
import xyz.catuns.dscms.orderservice.entity.Order;
import xyz.catuns.dscms.orderservice.entity.OrderStatus;
import xyz.catuns.dscms.orderservice.events.OrderEventProducer;
import xyz.catuns.dscms.orderservice.exception.CustomerNotFoundException;
import xyz.catuns.dscms.orderservice.exception.ProductNotFoundException;
import xyz.catuns.dscms.orderservice.mapper.OrderMapper;
import xyz.catuns.dscms.orderservice.repository.OrderLineRepository;
import xyz.catuns.dscms.orderservice.repository.OrderRepository;

@Service
@Profile("!dev")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final CustomerServiceClient customerServiceClient;
    private final ProductServiceClient productServiceClient;

    private final OrderEventProducer orderEventProducer;

    private final OrderMapper orderMapper;


    public CreateOrderResponse createOrder(CreateOrderRequest request) {

        // make request to user service
        ResponseEntity<CustomerDto> customerResponse = customerServiceClient.getCustomerById(
                request.customerId()
        );

        if (!customerResponse.getStatusCode().is2xxSuccessful() || !customerResponse.hasBody()) {
            throw new CustomerNotFoundException(request.customerId());
        }

        // check if products exists
        ResponseEntity<Boolean> productResponse = productServiceClient.validateOrderLines(request.lines());
        if (!productResponse.getStatusCode().is2xxSuccessful()
                || !productResponse.hasBody()
                || Boolean.FALSE.equals(productResponse.getBody())
        ) {
            throw new ProductNotFoundException("Could not validate product ids");
        }

//        List<CompletableFuture<ProductDto>> productFutures = request.lines().stream()
//                .map(line -> CompletableFuture.supplyAsync(() -> getProductDto(line)))
//                .toList();

//        CompletableFuture.allOf(productFutures.toArray(new CompletableFuture[0]))
//                .join();

        Order order = orderMapper.toEntity(request);
        order.setCustomer(customerResponse.getBody());

//        List<OrderLine> orderLines = orderLineMapper.toEntityList(request.lines());
//        order.setOrderLines(orderLines);

        order = orderRepository.save(order);

//        orderLineRepository.saveAll(orderLines);

        orderEventProducer.sendOrderCreatedEvent(order);

        return orderMapper.toCreateOrderResponse(order);
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
