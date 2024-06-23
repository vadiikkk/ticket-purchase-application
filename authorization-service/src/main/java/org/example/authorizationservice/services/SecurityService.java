package org.example.authorizationservice.services;

import lombok.AllArgsConstructor;
import org.example.authorizationservice.entities.Session;
import org.example.authorizationservice.entities.User;
import org.example.authorizationservice.exceptions.*;
import org.example.authorizationservice.repositories.SessionRepository;
import org.example.authorizationservice.repositories.UserRepository;
import org.example.authorizationservice.security.JwtCore;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class SecurityService {

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final String PASSWORD_REGEX =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    private final UserRepository userRepository;

    private final SessionRepository sessionRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtCore jwtCore;

    public void checkNickname(String nickname) {
        if (nickname == null) {
            throw new InvalidNicknameException("Not valid nickname");
        }
        if (userRepository.existsByNickname(nickname)) {
            throw new NicknameAlreadyExistsException("Such nickname already exists");
        }
    }

    public void checkEmail(String email) {
        Pattern pat = Pattern.compile(EMAIL_REGEX);
        if (email == null || !pat.matcher(email).matches()) {
            throw new InvalidEmailException("Not valid email");
        }

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Such email already exists");
        }
    }

    public void checkPassword(String password) {
        Pattern pat = Pattern.compile(PASSWORD_REGEX);
        if (password == null || !pat.matcher(password).matches()) {
            throw new InvalidPasswordException("Not valid password");
        }
    }

    public void registerUser(String nickname, String email, String password) {
        checkNickname(nickname);
        checkEmail(email);
        checkPassword(password);

        User user = new User();
        user.setNickname(nickname);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public void logUser(String nickname, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(nickname, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateToken(authentication);
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not  found!", nickname)
        ));

        Session session = new Session();
        session.setUserId(user.getId());
        session.setToken(jwt);
        session.setExpires(LocalDateTime.now().plusSeconds(jwtCore.getLifetime() / 1000));
        sessionRepository.save(session);
    }
}
