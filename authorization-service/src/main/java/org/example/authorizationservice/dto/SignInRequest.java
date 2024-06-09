package org.example.authorizationservice.dto;

import lombok.Data;

@Data
public class SignInRequest {
    String nickname;
    String password;
}
