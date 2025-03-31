package com.vamberto.School.model;

import com.vamberto.School.model.enums.BookStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "book_copy")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, updatable = false )
    private  UUID book_id;

    @Column( nullable = false)
    private BookStatus bookStatus;

    @JoinColumn(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime UpdatedAt;

    @JoinColumn(updatable = false, nullable = false)
    private UUID createdBy;

    private UUID updatedBy;

}
