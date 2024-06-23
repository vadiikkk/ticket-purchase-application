package org.example.authorizationservice.services;

import lombok.AllArgsConstructor;
import org.example.authorizationservice.entities.User;
import org.example.authorizationservice.repositories.UserRepository;
import org.example.authorizationservice.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByNickname(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not  found!", username)
        ));
        return UserDetailsImpl.build(user);
    }
}
