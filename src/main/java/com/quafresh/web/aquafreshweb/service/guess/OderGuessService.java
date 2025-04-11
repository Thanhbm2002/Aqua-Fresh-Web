package com.quafresh.web.aquafreshweb.service.guess;

import com.quafresh.web.aquafreshweb.dto.guess.OrderDetailClientDTO;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface OderGuessService {
    public ResponseEntity<String> addOrder(OrderDetailClientDTO orderDetailClientDTO);
    public BigDecimal updateTotalQuantity(Integer orderId);
}
