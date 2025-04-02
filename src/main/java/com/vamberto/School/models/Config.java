package com.vamberto.School.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Config {
    @Id
    @Column(unique = true, nullable = false, updatable = false, length = 100)
    private String id;

    @Column(nullable = false, length = 100)
    private String valor;
}
