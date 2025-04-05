package com.quafresh.web.aquafreshweb.service.guess;

import com.quafresh.web.aquafreshweb.entity.ProductDetail;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductDetailService {
    public ResponseEntity<List<ProductDetail>> getAll();
}
