package xyz.catuns.dscms.orderservice.exception;

public class CustomerNotFoundException extends NotFoundException {
    public CustomerNotFoundException(Long customerId) {
        super("Customer with id %d not found".formatted(customerId));
    }
}
