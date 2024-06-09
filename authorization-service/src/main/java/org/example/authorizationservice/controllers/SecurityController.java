package org.example.authorizationservice.controllers;

import lombok.AllArgsConstructor;
import org.example.authorizationservice.dto.SignInRequest;
import org.example.authorizationservice.dto.SignUpRequest;
import org.example.authorizationservice.services.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class SecurityController {

    private final SecurityService securityService;

    @PostMapping("/reg")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        securityService.registerUser(signUpRequest.getNickname(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        return ResponseEntity.ok("User was registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        securityService.logUser(signInRequest.getNickname(), signInRequest.getPassword());

        return ResponseEntity.ok("User was logged in successfully!");
    }

}
