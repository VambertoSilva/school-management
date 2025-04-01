package com.vamberto.School.controllers;

import com.vamberto.School.models.Book;
import com.vamberto.School.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book body){
        Book response = bookService.createBook(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PatchMapping("{id}")
        public ResponseEntity<Book> update(@RequestBody Book body, @PathVariable String id){
        Book response = bookService.updateBook(id, body);

        return ResponseEntity.ok(body);
    }

    @GetMapping
    public ResponseEntity <List<Book>> bookList(){
        List<Book> list = bookService.listBook();

        return  ResponseEntity.ok(list);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){

        bookService.delete(id);
        return  ResponseEntity.noContent().build();

    }


}
