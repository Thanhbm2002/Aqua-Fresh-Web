package com.quafresh.web.aquafreshweb.service.Impl;

import com.quafresh.web.aquafreshweb.dto.guess.CartGuessDTO;
import com.quafresh.web.aquafreshweb.entity.Cart;
import com.quafresh.web.aquafreshweb.entity.ProductDetail;
import com.quafresh.web.aquafreshweb.entity.User;
import com.quafresh.web.aquafreshweb.repositories.CartRepository;
import com.quafresh.web.aquafreshweb.repositories.ProductDetailRepository;
import com.quafresh.web.aquafreshweb.repositories.UserRepository;
import com.quafresh.web.aquafreshweb.service.guess.CartServiceGuess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartGuessImpl implements CartServiceGuess {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Override
    public ResponseEntity<List<Cart>> getCartByUserId(Integer id) {
        try {
            User user = userRepository.findById(id).get();
            return new ResponseEntity<>(cartRepository.getCartByIdUser(user), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Cart> save(CartGuessDTO cart) {
        try {
            ProductDetail productDetail = productDetailRepository.findById(cart.getIdProductDetail()).get();
            User user = userRepository.findById(cart.getIdUSer()).get();
            Cart cart1 = new Cart();
            cart1.setDateAdded(new Date().toInstant());
            cart1.setIdProductDetail(productDetail);
            cart1.setIdUser(user);
            cart1.setQuantity(cart.getQuantity());
            return new ResponseEntity<>(cartRepository.save(cart1), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Cart(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteById(Integer id) {
        try {
            cartRepository.deleteById(id);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Delete failed", HttpStatus.NOT_FOUND);
        }
    }
}

