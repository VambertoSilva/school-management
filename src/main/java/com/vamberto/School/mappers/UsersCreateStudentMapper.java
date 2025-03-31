package com.vamberto.School.mappers;

import com.vamberto.School.dtos.UsersCreateStudentDTO;
import com.vamberto.School.models.Users;
import com.vamberto.School.models.enums.UsersRole;

public class UsersCreateStudentMapper {

    public static Users toEntity(UsersCreateStudentDTO dto) {
        Users user = new Users();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setName(dto.name());
        user.setUsersRole(UsersRole.STUDENT);
        user.setPassword(dto.password()); // ⚠️ Criptografe antes de salvar no banco!
        return user;
    }
}
