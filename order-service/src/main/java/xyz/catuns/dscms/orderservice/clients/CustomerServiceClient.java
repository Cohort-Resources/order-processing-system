package xyz.catuns.dscms.orderservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.catuns.dscms.orderservice.dto.CustomerDto;

@FeignClient(name = "CUSTOMER-SERVICE", url = "http://localhost:8181") // todo: use hostname
public interface CustomerServiceClient {

    @GetMapping("/api/customers/{customerId}")
    ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long customerId);
}
