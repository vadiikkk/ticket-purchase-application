package org.example.orderservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "from_station_id", nullable = false)
    private Integer fromStationId;

    @Column(name = "to_station_id", nullable = false)
    private Integer toStationId;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
    }
}
