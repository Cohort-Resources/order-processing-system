package xyz.catuns.dscms.orderservice.clients;

import org.springframework.http.ResponseEntity;
import xyz.catuns.dscms.orderservice.dto.CustomerDto;

public interface CustomerServiceClient {
    ResponseEntity<CustomerDto> getCustomerById(Long customerId);
}
