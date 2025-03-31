package com.vamberto.School.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(length = 255)
    private String author;

    @Column(name = "publish_date")
    private LocalDate publishDate;


    @Column(name = "classification_id")
    private Classification classificationId;


    @Column(name = "created_by")
    private UUID createdBy;


    @Column(name = "updated_by")
    private UUID updatedBy;

    @CreatedDate
    private LocalDateTime  createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // Getters and Setters
}
