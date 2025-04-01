package com.quafresh.web.aquafreshweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "Order_Detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_order", nullable = false)
    private OrderTable idOrder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_product_detail", nullable = false)
    private ProductDetail idProductDetail;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @ColumnDefault("1")
    @Column(name = "quantity")
    private Integer quantity;

}