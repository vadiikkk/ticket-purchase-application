package org.example.authorizationservice.services;

import lombok.AllArgsConstructor;
import org.example.authorizationservice.dto.TokenResponse;
import org.example.authorizationservice.entities.Session;
import org.example.authorizationservice.exceptions.SessionNotFoundException;
import org.example.authorizationservice.repositories.SessionRepository;
import org.example.authorizationservice.security.UserDetailsImpl;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    private final UserService userService;

    public TokenResponse getUserData(Principal principal) {
        UserDetailsImpl userDetails = (UserDetailsImpl) userService.loadUserByUsername(principal.getName());

        Session session = sessionRepository.findByUserId(userDetails.getId()).orElseThrow(() -> new SessionNotFoundException(
                "Session not found!"));

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setUserId(userDetails.getId());
        tokenResponse.setExpired(session.getExpires());

        return tokenResponse;
    }
}
