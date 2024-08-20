package com.yhdc.batch_scheduler.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Table(name = "customer")
@Entity
public class Customer {

    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "BINARY(16)", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Status status;

    public enum Status {
        ACTIVE, DORMANT
    }

    @Column(name = "created")
    private LocalDateTime createdAt;

    @Column(name = "last_login")
    private LocalDateTime loginAt;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.createdAt = LocalDateTime.now();
        this.loginAt = LocalDateTime.now();
        this.status = Status.ACTIVE;
    }

    public void setLoginAt(LocalDateTime loginAt) {
        this.loginAt = loginAt;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
