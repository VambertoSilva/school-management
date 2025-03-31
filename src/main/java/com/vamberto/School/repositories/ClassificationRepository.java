package com.vamberto.School.repositories;

import com.vamberto.School.models.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClassificationRepository extends JpaRepository<Classification, UUID> {
}
