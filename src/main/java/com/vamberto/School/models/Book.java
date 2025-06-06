package com.vamberto.School.models;

import com.vamberto.School.models.enums.BookStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
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

    @Column(length = 255, nullable = false)
    private String author;

    @Column(name = "publish_date", nullable = false)
    private LocalDate publishDate;


    @Column(name = "classification_id")
    private UUID classificationId;

    @Column(nullable = false, length = 40)
    private BookStatus status;

}
