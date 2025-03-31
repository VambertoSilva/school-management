package com.vamberto.School.mappers;

import com.vamberto.School.dtos.UsersDTO;
import com.vamberto.School.models.Users;

public class UsersMapper {

    public static UsersDTO toDTO (Users user){
        return new UsersDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getUsersRole()
        );
    }
}
