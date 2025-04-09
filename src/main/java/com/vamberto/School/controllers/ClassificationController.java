package com.vamberto.School.controllers;

import com.vamberto.School.models.Classification;
import com.vamberto.School.services.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/classification")
@RequiredArgsConstructor
public class ClassificationController {

    private final LibraryService libraryService;

    @PreAuthorize("hasAnyRole('LIBRARIAN, DIRECTOR')")
    @PostMapping
    public ResponseEntity<Classification> createClassification(@RequestBody Classification body){

         Classification response = libraryService.createClassification(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PreAuthorize("hasAnyRole('LIBRARIAN, DIRECTOR')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClassification(@PathVariable String id){

        libraryService.removeClassification(UUID.fromString(id));

        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<Classification>> listClassification (){
         List<Classification> list = libraryService.listClassification();

         return ResponseEntity.ok().body(list);
    }
 }
