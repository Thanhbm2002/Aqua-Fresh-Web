package com.quafresh.web.aquafreshweb.controller;

import com.quafresh.web.aquafreshweb.dto.ProductDetailDTO;
import com.quafresh.web.aquafreshweb.entity.ProductDetail;
import com.quafresh.web.aquafreshweb.service.Impl.ProductDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductDetailImpl productDetail;
    @GetMapping
    public ResponseEntity<List<ProductDetail>> getAllProduct(){
        return productDetail.getAll();
    }
}
