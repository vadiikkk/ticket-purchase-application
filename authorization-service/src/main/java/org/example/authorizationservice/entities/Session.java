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
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "expires", nullable = false)
    private LocalDateTime expires;
}