package org.example.authorizationservice.services;

import org.example.authorizationservice.entities.User;
import org.example.authorizationservice.repositories.UserRepository;
import org.example.authorizationservice.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByNickname(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not  found!", username)
        ));
        return UserDetailsImpl.build(user);
    }
}
