package com.quafresh.web.aquafreshweb._config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
@Setter
@Getter
public class JwtConfig {
    private String secret;
    private long expiration;

    @PostConstruct
    public void init() {
        System.out.println("JWT Secret: " + (secret == null ? "NULL" : secret));
        System.out.println("JWT Expiration: " + expiration);
    }
}
