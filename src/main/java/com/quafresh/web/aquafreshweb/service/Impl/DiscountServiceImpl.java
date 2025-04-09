package com.quafresh.web.aquafreshweb.service.Impl;

import com.quafresh.web.aquafreshweb.entity.Discount;
import com.quafresh.web.aquafreshweb.repositories.DiscountRepository;
import com.quafresh.web.aquafreshweb.service.guess.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    DiscountRepository discountRepository;
    @Override
    public ResponseEntity<List<Discount>> getAllDiscount() {
        try {
            return new ResponseEntity<>(discountRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    }
