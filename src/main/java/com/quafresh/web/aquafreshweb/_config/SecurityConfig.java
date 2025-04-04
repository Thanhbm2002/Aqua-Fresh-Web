package com.quafresh.web.aquafreshweb._config;

import com.quafresh.web.aquafreshweb.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthService authService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/user/**").hasAnyAuthority("ROLE_USER")// Các URL này phải yêu cầu xác thực
                                .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN") // Các URL này phải yêu cầu xác thực

                                .anyRequest().permitAll()  // Các URL vào tự do
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // Thêm filter JWT vào trước UsernamePasswordAuthenticationFilter

        return http.build();  // Trả về SecurityFilterChain đã cấu hình
    }
    // Cấu hình AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(authService);  // Cung cấp UserDetailsService để xác thực người dùng
        return authenticationManagerBuilder.build();  // Trả về AuthenticationManager đã cấu hình
    }

}