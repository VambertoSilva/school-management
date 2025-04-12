package com.vamberto.School.configs;

import com.vamberto.School.models.Users;
import com.vamberto.School.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String login){
        Users user = usersRepository.findByUsername(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new CustomUserDetails(
                user,
                List.of(() -> "ROLE_" + user.getUsersRole().name()) // convertendo enum em GrantedAuthority
        );
    }
}
