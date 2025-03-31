package com.vamberto.School.controllers;

import com.vamberto.School.dtos.UsersCreateStudentDTO;
import com.vamberto.School.dtos.UsersDTO;
import com.vamberto.School.mappers.UsersCreateStudentMapper;
import com.vamberto.School.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<UsersDTO> create(@RequestBody UsersCreateStudentDTO dto){

        UsersDTO response = usersService.save(UsersCreateStudentMapper.toEntity(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
