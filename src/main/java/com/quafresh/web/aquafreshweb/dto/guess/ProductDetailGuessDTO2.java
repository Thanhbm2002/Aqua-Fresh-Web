package com.quafresh.web.aquafreshweb.dto.guess;

import com.quafresh.web.aquafreshweb.entity.Picture;
import com.quafresh.web.aquafreshweb.entity.Product;
import com.quafresh.web.aquafreshweb.entity.ProductDetail;
import lombok.Data;

import java.util.List;
@Data
public class ProductDetailGuessDTO2 {
    private ProductDetail productDetail;
    private List<Picture> pictures;
}
