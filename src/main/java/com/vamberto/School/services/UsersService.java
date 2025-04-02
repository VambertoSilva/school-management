package com.vamberto.School.services;

import com.vamberto.School.dtos.UsersDTO;
import com.vamberto.School.mappers.UsersMapper;
import com.vamberto.School.models.Users;
import com.vamberto.School.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersDTO save(Users user){

        usersRepository.save(user);

        return UsersMapper.toDTO(user);
    }

    public List<UsersDTO> list() {
        return usersRepository
                .findAll()
                .stream()
                .map(user -> new UsersDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getName(),
                        user.getUsersRole()))
                .collect(Collectors.toList());
    }
}
