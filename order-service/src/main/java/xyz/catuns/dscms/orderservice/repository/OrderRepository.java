package xyz.catuns.dscms.orderservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import xyz.catuns.dscms.orderservice.entity.Order;
import xyz.catuns.dscms.orderservice.entity.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long>{
    Page<Order> findAllByIdAndStatus(@Param("id") Long customerId, @Param("status") OrderStatus status, Pageable pageable);
}
