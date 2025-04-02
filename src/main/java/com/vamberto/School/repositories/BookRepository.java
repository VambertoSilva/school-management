package com.vamberto.School.repositories;

import com.vamberto.School.models.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {


    Page<Book> findAll(Pageable pageable);
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
