package org.example.authorizationservice.controllers;

import lombok.AllArgsConstructor;
import org.example.authorizationservice.dto.TokenResponse;
import org.example.authorizationservice.services.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/session")
public class SessionController {

    private final SessionService sessionService;

    @GetMapping
    public ResponseEntity<?> getUserData(Principal principal) {
        TokenResponse tokenResponse = sessionService.getUserData(principal);

        return ResponseEntity.ok(tokenResponse);
    }
}
