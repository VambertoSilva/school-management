package com.vamberto.School.repositories;

import com.vamberto.School.models.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config, String> {
}
