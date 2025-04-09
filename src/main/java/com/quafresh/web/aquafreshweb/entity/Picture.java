package com.quafresh.web.aquafreshweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "url_image", length = 500)
    private String urlImage;

    @ManyToOne
    @JoinColumn(name = "id_product_detail")
    private ProductDetail idProductDetail;

    @ColumnDefault("b'1'")
    @Column(name = "status")
    private Boolean status;

}