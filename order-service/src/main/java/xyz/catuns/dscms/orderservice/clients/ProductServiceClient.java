package xyz.catuns.dscms.orderservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.catuns.dscms.orderservice.dto.ProductDto;
import xyz.catuns.dscms.orderservice.dto.requests.OrderLineRequest;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE", url = "http://localhost:8182") // uses hostname
public interface ProductServiceClient {

    @GetMapping("/api/products/{productId}")
    ResponseEntity<ProductDto> getProductById(@PathVariable Long productId);

    @GetMapping("/api/products/validate")
    ResponseEntity<Boolean> validateOrderLines(List<OrderLineRequest> lines);
}
