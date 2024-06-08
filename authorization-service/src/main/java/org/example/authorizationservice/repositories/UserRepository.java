package org.example.authorizationservice.repositories;


import org.example.authorizationservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByNickname(String nickname);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String nickname);

}
