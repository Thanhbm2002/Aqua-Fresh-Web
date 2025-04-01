package com.quafresh.web.aquafreshweb;

import com.quafresh.web.aquafreshweb._config.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class AquaFreshWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AquaFreshWebApplication.class, args);
    }

}
