package com.quafresh.web.aquafreshweb.service.guess;

import com.quafresh.web.aquafreshweb.entity.Discount;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DiscountService {
    public ResponseEntity<List<Discount>> getAllDiscount();
}
