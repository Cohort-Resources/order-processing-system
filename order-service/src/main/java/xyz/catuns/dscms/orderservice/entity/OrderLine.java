package xyz.catuns.dscms.orderservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import xyz.catuns.dscms.orderservice.dto.ProductDto;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "order_lines")
public class OrderLine {

    @Id
    @Setter(value = AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {PERSIST, DETACH, REFRESH, MERGE})
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "product_id")
    private Long productId;

    @Transient
    private ProductDto product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "line_amount")
    private Double lineAmount;

    @Column(name = "status")
    private OrderStatus status;

}