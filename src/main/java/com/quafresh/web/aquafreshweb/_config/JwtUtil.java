package com.quafresh.web.aquafreshweb._config;

import com.quafresh.web.aquafreshweb.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.secretKey = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    // Tạo token chứa username và role
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole() != null ? user.getRole() : false)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    // Giải mã token và lấy username
    public String extractToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token đã hết hạn!");
        } catch (JwtException e) {
            throw new RuntimeException("Token không hợp lệ!");
        }
    }

    // Kiểm tra token có hợp lệ không
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            throw new RuntimeException("Token không hợp lệ hoặc đã hết hạn!");
        }
    }

    // Lấy toàn bộ claims từ token
    public Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    // Lấy role từ token
    public Boolean getUserRole(String token) {
        Claims claims = getClaims(token);
        if (claims != null && claims.containsKey("role")) {
            Object roleObject = claims.get("role");
            if (roleObject instanceof Boolean) {
                return (Boolean) roleObject;
            } else if (roleObject instanceof Integer) {
                return ((Integer) roleObject) != 0;
            } else if (roleObject instanceof String) {
                return Boolean.parseBoolean((String) roleObject);
            }
        }
        throw new RuntimeException("Role không tồn tại hoặc không hợp lệ trong token!");
    }
}