package org.example.authorizationservice.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String nickname;
    private String email;
    private String password;
}
