package com.vamberto.School.dtos;

import com.vamberto.School.models.enums.UsersRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String name;
    private String usersRole; // se quiser passar o papel do usuário (opcional)
}