package com.quafresh.web.aquafreshweb.service.guess;

import com.quafresh.web.aquafreshweb.dto.guess.CartGuessDTO;
import com.quafresh.web.aquafreshweb.entity.Cart;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartServiceGuess {
    public ResponseEntity<List<Cart>> getCartByUserId (Integer id);

    ResponseEntity<Cart> save(CartGuessDTO cart);

    ResponseEntity<String> deleteById(Integer id);
}
