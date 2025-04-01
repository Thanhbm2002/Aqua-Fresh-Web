package com.quafresh.web.aquafreshweb.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {
    private final JwtConfig jwtConfig;

    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    // ✅ Tạo token chứa username + role
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)  // Thêm role vào JWT
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();
    }

    // ✅ Giải mã token (lấy username)
    public String extractToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            System.out.println("Token đã hết hạn!");
        } catch (UnsupportedJwtException e) {
            System.out.println("Token không hợp lệ!");
        } catch (MalformedJwtException e) {
            System.out.println("Token bị sửa đổi!");
        } catch (SignatureException e) {
            System.out.println("Chữ ký JWT không hợp lệ!");
        } catch (Exception e) {
            System.out.println("Lỗi không xác định: " + e.getMessage());
        }
        return null;
    }

    // ✅ Kiểm tra token có hợp lệ không
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Lấy toàn bộ claims từ token
    public Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    // ✅ Lấy role từ token
    public String getUserRole(String token) {
        Claims claims = getClaims(token);
        return claims != null ? claims.get("role", String.class) : null;
    }
}
