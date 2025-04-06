package com.quafresh.web.aquafreshweb.dto.guess;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailGuessDTO {
    private Integer pictureId;
    private String urlImage;
    private Boolean pictureStatus;
    private Integer productDetailId;
    private String productName;
    private BigDecimal price;
    private Integer quantity;
    private String description;    private Boolean productStatus;
}

