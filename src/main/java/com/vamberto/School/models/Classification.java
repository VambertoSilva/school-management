package com.vamberto.School.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;
}
