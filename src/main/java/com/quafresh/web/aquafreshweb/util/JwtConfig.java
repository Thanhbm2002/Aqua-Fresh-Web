package com.quafresh.web.aquafreshweb.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Setter
@Getter
public class JwtConfig {
    private String secret;
    private long expiration;

}
