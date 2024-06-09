package org.example.authorizationservice.repositories;

import org.example.authorizationservice.entities.Session;
import org.example.authorizationservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    Optional<Session> findByUserId(Integer userId);
}
