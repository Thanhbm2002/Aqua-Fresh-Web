package com.quafresh.web.aquafreshweb.service.Impl;

import com.quafresh.web.aquafreshweb.entity.ProductDetail;
import com.quafresh.web.aquafreshweb.repositories.ProductDetailRepository;
import com.quafresh.web.aquafreshweb.service.guess.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductDetailImpl implements ProductDetailService {
    @Autowired
    ProductDetailRepository productDetailRepository;
    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<List<ProductDetail>> getAll() {
        try {
//            List<ProductDetailDTO> dtoList = productDetailRepository.findAll()
//                    .stream()
//                    .map(ProductDetailDTO::new)
//                    .toList();

            return new ResponseEntity<>(productDetailRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
