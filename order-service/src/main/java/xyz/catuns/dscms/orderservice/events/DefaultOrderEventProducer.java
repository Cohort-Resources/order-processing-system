package xyz.catuns.dscms.orderservice.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import xyz.catuns.dscms.orderservice.entity.Order;

@Component
public class DefaultOrderEventProducer implements OrderEventProducer {

    private static final Logger log = LoggerFactory.getLogger(DefaultOrderEventProducer.class);

    @Override
    public void sendOrderCreatedEvent(Order order) {
        log.info("Sending event {}", order);

    }
}
