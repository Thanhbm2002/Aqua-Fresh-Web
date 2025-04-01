package com.quafresh.web.aquafreshweb._config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173", "http://localhost:5176")  // URL của frontend Vue.js
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Phương thức HTTP được phép
                .allowedHeaders("*");
    }
}
