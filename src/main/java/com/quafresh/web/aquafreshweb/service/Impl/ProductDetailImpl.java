package com.quafresh.web.aquafreshweb.service.Impl;

import com.quafresh.web.aquafreshweb.dto.guess.ProductDetailGuessDTO2;
import com.quafresh.web.aquafreshweb.entity.Picture;
import com.quafresh.web.aquafreshweb.entity.ProductDetail;
import com.quafresh.web.aquafreshweb.repositories.PictureRepository;
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
    @Autowired
    PictureRepository pictureRepository;
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

    @Override
    public ProductDetailGuessDTO2 getById(Integer id) {
        ProductDetail productDetail = productDetailRepository.findById(id).orElseThrow();
        List<Picture> pictureRepository1 = pictureRepository.findByIdProductDetail(productDetail);
        ProductDetailGuessDTO2 productDetailGuessDTO2 = new ProductDetailGuessDTO2();
        productDetailGuessDTO2.setProductDetail(productDetail);
        productDetailGuessDTO2.setPictures(pictureRepository1);
        return productDetailGuessDTO2;
    }
}
