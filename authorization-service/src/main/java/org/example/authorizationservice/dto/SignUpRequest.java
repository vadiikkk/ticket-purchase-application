package org.example.authorizationservice.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    String nickname;
    String email;
    String password;
}
