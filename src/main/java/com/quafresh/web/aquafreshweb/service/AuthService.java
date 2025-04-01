package com.quafresh.web.aquafreshweb.service;

import com.quafresh.web.aquafreshweb.dto.LoginDTO;
import com.quafresh.web.aquafreshweb.dto.LoginRequestDTO;
import com.quafresh.web.aquafreshweb.dto.RegisterDTO;
import com.quafresh.web.aquafreshweb.entity.User;
import com.quafresh.web.aquafreshweb.repositories.UserRepository;
import com.quafresh.web.aquafreshweb._config.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole() ? "ADMIN" : "USER")  // Thêm role của người dùng
                .build();
    }

    // Đăng ký người dùng
    public String register(RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already in use");
        }

        // Tạo người dùng mới và lưu vào database
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setFullname(registerDTO.getFullname());
        user.setEmail(registerDTO.getEmail());
        user.setPhone(registerDTO.getPhone());
        user.setRole(false);  // Giả sử mặc định là User (có thể thay đổi nếu muốn)
        userRepository.save(user);

        return "User registered successfully";
    }

    // Đăng nhập và tạo JWT
    public String login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username or password"));

        // Kiểm tra mật khẩu
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid password");
        }

        // Tạo JWT token
        return jwtUtil.generateToken(user);
    }
    public LoginRequestDTO getLoginInfo(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String token = jwtUtil.generateToken(user); // Tạo token mới
            return new LoginRequestDTO(
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getRole(),
                    token
            );
        }
        return null; // Trả về null nếu không tìm thấy user
    }


}