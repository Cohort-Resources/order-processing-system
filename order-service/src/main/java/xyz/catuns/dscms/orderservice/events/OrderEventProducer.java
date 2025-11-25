package xyz.catuns.dscms.orderservice.events;

import xyz.catuns.dscms.orderservice.entity.Order;

public interface OrderEventProducer {
    void sendOrderCreatedEvent(Order order);
}
