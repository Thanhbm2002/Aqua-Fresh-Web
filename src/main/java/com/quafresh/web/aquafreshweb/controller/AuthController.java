package com.quafresh.web.aquafreshweb.controller;

import com.quafresh.web.aquafreshweb._config.JwtUtil;
import com.quafresh.web.aquafreshweb.dto.LoginDTO;
import com.quafresh.web.aquafreshweb.dto.LoginRequestDTO;
import com.quafresh.web.aquafreshweb.dto.RegisterDTO;
import com.quafresh.web.aquafreshweb.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO registerDTO) {
        return authService.register(registerDTO);
    }

    @GetMapping("/login-info")
    public String loginInfo(){
        return "abcnksadsa";
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }
}