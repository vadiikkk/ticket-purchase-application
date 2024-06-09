package org.example.orderservice.security;

import org.apache.coyote.BadRequestException;
import org.example.orderservice.exceptions.AuthorizationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;


@Service
public class AuthorizationClient {

    private final WebClient webClient;

    private final Integer timeout;

    public AuthorizationClient(WebClient.Builder builder,
                               AuthorizationClientConfig configuration) {
        webClient = builder.baseUrl(configuration.getUrl()).build();
        timeout = configuration.getTimeout();
    }

    public UserDetailsImpl authenticate(String bearerHeader) {
        return webClient.get()
                .uri("/session")
                .header(HttpHeaders.AUTHORIZATION, bearerHeader)
                .retrieve()
                .onStatus(
                        status -> HttpStatus.UNAUTHORIZED.equals(status) ||  HttpStatus.FORBIDDEN.equals(status),
                        response -> response.bodyToMono(AuthorizationException.class)
                )
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        response -> response.bodyToMono(BadRequestException.class)
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        response -> response.bodyToMono(AuthorizationException.class)
                )
                .bodyToMono(UserDetailsImpl.class)
                .block(Duration.ofMillis(timeout));
    }

}
