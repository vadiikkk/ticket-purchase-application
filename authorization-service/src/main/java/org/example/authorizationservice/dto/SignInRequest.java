package org.example.authorizationservice.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String nickname;
    private String password;
}
