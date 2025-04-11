package com.quafresh.web.aquafreshweb.service;

import com.quafresh.web.aquafreshweb.dto.LoginDTO;
import com.quafresh.web.aquafreshweb.dto.LoginRequestDTO;
import com.quafresh.web.aquafreshweb.dto.RegisterDTO;
import com.quafresh.web.aquafreshweb.entity.User;
import com.quafresh.web.aquafreshweb.repositories.UserRepository;
import com.quafresh.web.aquafreshweb._config.JwtUtil;
import com.quafresh.web.aquafreshweb.util.AdminMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User detail = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
       return detail;
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
        String role = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("No role");
        System.out.println("User '" + user.getUsername() + "' logged in with role: " + role);
        // Tạo JWT token
        return jwtUtil.generateToken(user);
    }
    //Lấy thông tin user đăng nhập
    public LoginRequestDTO getLoginInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof User)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid user type");
        }

        User me = (User) principal;
        return adminMapper.toLoginRequestDTO(me);
    }

}