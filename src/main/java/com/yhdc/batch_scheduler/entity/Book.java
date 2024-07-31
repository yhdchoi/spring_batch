package com.yhdc.batch_scheduler.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "book")
@Entity
public class Book {

    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "BINARY(16)", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Column(name = "author", length = 200, nullable = false)
    private String author;

    @Column(name = "published", length = 10)
    private String published;

}
