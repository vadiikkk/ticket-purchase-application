package org.example.orderservice.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AuthorizationClientConfig {

    @Value("${testing.app.timeout}")
    private Integer timeout;

    @Value("${testing.app.url}")
    private String url;

}
