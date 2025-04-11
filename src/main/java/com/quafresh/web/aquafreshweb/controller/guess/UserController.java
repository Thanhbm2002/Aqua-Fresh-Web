package com.quafresh.web.aquafreshweb.controller.guess;

import com.quafresh.web.aquafreshweb.dto.LoginRequestDTO;
import com.quafresh.web.aquafreshweb.entity.User;
import com.quafresh.web.aquafreshweb.service.AuthService;
import com.quafresh.web.aquafreshweb.service.Impl.UserGuessImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/user")
@RestController
@AllArgsConstructor
public class UserController {
    private final AuthService authService;
    private final UserGuessImpl userGuess;

    @PostMapping("/edit")
    public ResponseEntity<User> editUser(@RequestBody User user){
        return ResponseEntity.ok(userGuess.editUser(user));
    }
}
