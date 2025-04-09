package com.quafresh.web.aquafreshweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Product_Detail")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "id_product", nullable = false)
    private Product idProduct;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ColumnDefault("0")
    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_color")
    private Color idColor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_technology")
    private Technology idTechnology;

    @Column(name = "description", length = 500)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_discount")
    private Discount idDiscount;

    @ColumnDefault("b'1'")
    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "idProductDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Picture> listUrl = new ArrayList<>();

    public void addPicture(Picture picture) {
        listUrl.add(picture);
    }
    public void removePicture(Picture picture) {
        listUrl.remove(picture);
    }
}