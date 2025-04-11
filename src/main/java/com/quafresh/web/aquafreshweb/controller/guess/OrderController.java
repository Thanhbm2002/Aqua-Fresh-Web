package com.quafresh.web.aquafreshweb.controller.guess;

import com.quafresh.web.aquafreshweb.dto.guess.OrderDetailClientDTO;
import com.quafresh.web.aquafreshweb.service.Impl.OderGuessImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order")
@AllArgsConstructor
@RestController
public class OrderController {
    private final OderGuessImpl orOderGuess;
    @PostMapping
    public ResponseEntity<String> addOrder (@RequestBody OrderDetailClientDTO orderDetailClientDTO){
        return  orOderGuess.addOrder(orderDetailClientDTO);
    }
}
