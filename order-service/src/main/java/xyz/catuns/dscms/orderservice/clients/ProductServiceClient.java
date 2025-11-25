package xyz.catuns.dscms.orderservice.clients;

import org.springframework.http.ResponseEntity;
import xyz.catuns.dscms.orderservice.dto.ProductDto;
import xyz.catuns.dscms.orderservice.dto.requests.OrderLineRequest;

import java.util.List;

public interface ProductServiceClient {

    ResponseEntity<ProductDto> getProductById(Long productId);

    ResponseEntity<Boolean> validateOrderLines(List<OrderLineRequest> lines);
}
