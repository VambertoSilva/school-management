package com.vamberto.School.controllers;

import com.vamberto.School.models.Book;
import com.vamberto.School.models.enums.BookStatus;
import com.vamberto.School.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/school/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PreAuthorize("hasAnyRole('LIBRARIAN', 'DIRECTOR')")
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book body){
        Book response = bookService.createBook(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PreAuthorize("hasAnyRole('LIBRARIAN', 'DIRECTOR')")
    @PutMapping("{id}")
        public ResponseEntity<Book> update(@RequestBody Book body, @PathVariable String id){
        Book response = bookService.updateBook(id, body);

        return ResponseEntity.ok(body);
    }

    @PreAuthorize("hasAnyRole('LIBRARIAN', 'DIRECTOR')")
    @GetMapping
    public ResponseEntity <Page<Book>> bookList(Pageable pageable){
        Page<Book> list = bookService.listBook(pageable);

        return  ResponseEntity.ok(list);
    }

    @GetMapping("/search")
    public Page<Book> searchBooks(
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(defaultValue = "AVAILABLE") BookStatus filter
    ) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();
        Object principal = auth.getPrincipal();


        System.out.println("Usuário logado: " + username);
        System.out.println(principal);
        return bookService.searchBooks(title, page, size, sortBy, direction, filter);
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){

        bookService.delete(id);
        return  ResponseEntity.noContent().build();

    }


}
