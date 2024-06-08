package org.example.authorizationservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "_session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user_id;

    @Column(name = "token", nullable = false)
    String token;

    @Column(name = "expires", nullable = false)
    LocalDateTime expires;
}