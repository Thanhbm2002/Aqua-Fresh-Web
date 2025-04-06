package com.quafresh.web.aquafreshweb.service.guess;

import com.quafresh.web.aquafreshweb.dto.guess.ProductDetailGuessDTO;
import com.quafresh.web.aquafreshweb.entity.Picture;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface PictureGuessService {
    public ResponseEntity<List<Picture>> getAllPicture();
    public ResponseEntity<List<ProductDetailGuessDTO>> getAllPictureProductDetail(Integer technologyId,
                                                                                  String productName,
                                                                                  BigDecimal priceFrom,
                                                                                  BigDecimal priceTo);

    ResponseEntity<List<ProductDetailGuessDTO>> getAllByProductId(Integer id);
}
