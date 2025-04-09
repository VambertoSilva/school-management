package com.vamberto.School.controllers;

import com.vamberto.School.dtos.UsersCreateStudentDTO;
import com.vamberto.School.dtos.UsersDTO;
import com.vamberto.School.mappers.UsersCreateStudentMapper;
import com.vamberto.School.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PreAuthorize("hasAnyRole('LIBRARIAN, DIRECTOR')")
    @PostMapping
    public ResponseEntity<UsersDTO> create(@RequestBody UsersCreateStudentDTO dto){

        UsersDTO response = usersService.save(UsersCreateStudentMapper.toEntity(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UsersDTO>> list(){
        List<UsersDTO> response = usersService.list();

        return ResponseEntity.ok(response);
    }
}
