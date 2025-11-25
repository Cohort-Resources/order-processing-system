package xyz.catuns.dscms.orderservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import xyz.catuns.dscms.orderservice.dto.CustomerDto;
import xyz.catuns.dscms.orderservice.dto.UserDto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Setter(value = AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "created_by_user")
    private UUID createdByUser;

    @Column(name = "order_number", length = 64, unique = true)
    private String orderNumber;

    @Column(name = "status", length = 32)
    private OrderStatus status;

    @Column(name = "total_amount")
    private Double totalAmount;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL})
    private List<OrderLine> orderLines = new ArrayList<>();

    @Transient
    private CustomerDto customer;

    @Transient
    private UserDto user;

}
