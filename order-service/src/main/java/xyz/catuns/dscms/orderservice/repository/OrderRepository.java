package xyz.catuns.dscms.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.catuns.dscms.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
}
