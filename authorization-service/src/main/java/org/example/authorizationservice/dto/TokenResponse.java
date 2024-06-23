package org.example.authorizationservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TokenResponse {
    private Integer userId;
    private LocalDateTime expired;
}
