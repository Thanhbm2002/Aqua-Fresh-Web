package com.quafresh.web.aquafreshweb.dto;

import com.quafresh.web.aquafreshweb.entity.ProductDetail;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ProductDetailDTO {
    private Integer id;
    private String productName;
    private BigDecimal price;
    private Integer quantity;
    private String colorName;
    private String technologyName;
    private String description;
    private String discountType;
    private BigDecimal discountValue;
    private Boolean status;

    public ProductDetailDTO(ProductDetail productDetail) {
        this.id = productDetail.getId();
        this.productName = productDetail.getIdProduct().getProductName();
        this.price = productDetail.getPrice();
        this.quantity = productDetail.getQuantity();
        this.colorName = productDetail.getIdColor() != null ? productDetail.getIdColor().getName() : null;
        this.technologyName = productDetail.getIdTechnology() != null ? productDetail.getIdTechnology().getName() : null;
        this.description = productDetail.getDescription();
        this.discountType = productDetail.getDiscountType();
        this.discountValue = productDetail.getDiscountValue();
        this.status = productDetail.getStatus();
    }
}
