package com.quafresh.web.aquafreshweb.controller.guess;

import com.quafresh.web.aquafreshweb.dto.guess.CartGuessDTO;
import com.quafresh.web.aquafreshweb.entity.Cart;
import com.quafresh.web.aquafreshweb.service.Impl.CartGuessImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user/cart")
@AllArgsConstructor
@RestController
@PreAuthorize("hasRole('ROLE_USER')")
public class CartController {
    private final CartGuessImpl cartGuess;
    //API GetCartByUserId
    @GetMapping("/{id}")
    public ResponseEntity<List<Cart>> getByUserId(@PathVariable Integer id){
        return cartGuess.getCartByUserId(id);
    }

    @PostMapping
    public ResponseEntity<Cart> addCartToDB (@RequestBody CartGuessDTO cart){
        return cartGuess.save(cart);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id){
        return cartGuess.deleteById(id);
    }
}
