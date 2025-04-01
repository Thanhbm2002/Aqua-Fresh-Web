package com.quafresh.web.aquafreshweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category")
    private Category idCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_company")
    private Company idCompany;

    @ColumnDefault("b'1'")
    @Column(name = "status")
    private Boolean status;

}