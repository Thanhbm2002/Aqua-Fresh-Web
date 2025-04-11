package com.quafresh.web.aquafreshweb._config;

import com.quafresh.web.aquafreshweb.service.AuthService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String requestPath = request.getServletPath();

        // Bỏ qua kiểm tra JWT với các URL không yêu cầu xác thực
        if (requestPath.equals("/auth/register") || requestPath.equals("/auth/login")) {
            chain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Loại bỏ "Bearer " ở đầu token
            try {
                if (jwtUtil.validateToken(token)) {
                    String username = jwtUtil.extractToken(token);

                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        // Lấy thông tin người dùng từ AuthService
                        var userDetail = authService.loadUserByUsername(username);

                        // Tạo đối tượng Authentication
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetail,
                                null,
                                userDetail.getAuthorities() // Cung cấp quyền của người dùng
                        );

                        // Đặt Authentication vào SecurityContext
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        System.out.println("UserDetail: " + userDetail);
                    }
                }
            } catch (ExpiredJwtException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token đã hết hạn.");
                return;
            } catch (JwtException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token không hợp lệ.");
                return;
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi xử lý token: " + e.getMessage());
                return;
            }
        }

        // Tiếp tục xử lý request
        chain.doFilter(request, response);
    }
}
