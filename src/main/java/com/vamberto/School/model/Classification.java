package com.vamberto.School.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(length = 255)
    private String description;
}
