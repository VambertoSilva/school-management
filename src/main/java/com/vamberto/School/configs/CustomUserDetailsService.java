package com.vamberto.School.configs;

import com.vamberto.School.models.Users;
import com.vamberto.School.repositories.UsersRepository;
import com.vamberto.School.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String login){
        Optional<Users> usersOptional= usersRepository.findByUsername(login);

        if (usersOptional.isEmpty()){
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }

        Users user = usersOptional.get();

        System.out.println("User: " + user );

        return User.builder().username(user.getUsername()).password((user.getPassword())).roles(String.valueOf(user.getUsersRole())).build();
    }
}
