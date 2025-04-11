package com.quafresh.web.aquafreshweb.controller;

import com.quafresh.web.aquafreshweb._config.JwtUtil;
import com.quafresh.web.aquafreshweb.dto.LoginDTO;
import com.quafresh.web.aquafreshweb.dto.LoginRequestDTO;
import com.quafresh.web.aquafreshweb.dto.RegisterDTO;
import com.quafresh.web.aquafreshweb.entity.User;
import com.quafresh.web.aquafreshweb.service.AuthService;
import com.quafresh.web.aquafreshweb.service.Impl.UserGuessImpl;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserGuessImpl userGuess;
    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO registerDTO) {
        return authService.register(registerDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @GetMapping("/me")
    public ResponseEntity<LoginRequestDTO> loginInfo() {
        return ResponseEntity.ok(authService.getLoginInfo());
    }

}