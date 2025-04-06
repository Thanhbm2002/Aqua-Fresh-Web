package com.quafresh.web.aquafreshweb.service.guess;

import com.quafresh.web.aquafreshweb.dto.guess.ProductDetailGuessDTO2;
import com.quafresh.web.aquafreshweb.entity.ProductDetail;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductDetailService {
    public ResponseEntity<List<ProductDetail>> getAll();
    public ProductDetailGuessDTO2 getById(Integer id);
}
