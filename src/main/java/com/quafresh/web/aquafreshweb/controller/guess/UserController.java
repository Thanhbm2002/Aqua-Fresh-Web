package com.quafresh.web.aquafreshweb.controller.guess;

import com.quafresh.web.aquafreshweb.dto.LoginRequestDTO;
import com.quafresh.web.aquafreshweb.entity.User;
import com.quafresh.web.aquafreshweb.service.AuthService;
import com.quafresh.web.aquafreshweb.service.Impl.UserGuessImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@AllArgsConstructor
public class UserController {
    private final AuthService authService;
    private final UserGuessImpl userGuess;
    @GetMapping("/me")
    public ResponseEntity<LoginRequestDTO> loginInfo() {
        return ResponseEntity.ok(authService.getLoginInfo());
    }

    @PostMapping("/edit")
    public ResponseEntity<User> editUser(@RequestBody User user){
        return ResponseEntity.ok(userGuess.editUser(user));
    }
}
