package com.vamberto.School.controllers;


import com.vamberto.School.configs.CustomUserDetailsService;
import com.vamberto.School.dtos.AuthenticationRequest;
import com.vamberto.School.dtos.AuthenticationResponse;
import com.vamberto.School.dtos.RegisterRequest;
import com.vamberto.School.models.Users;
import com.vamberto.School.models.enums.UsersRole;
import com.vamberto.School.repositories.UsersRepository;
import com.vamberto.School.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request) {
        // 1. Autentica usuário
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // 2. Busca detalhes do usuário (para gerar o token)
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        // 3. Gera token
        String token = jwtService.generateToken(userDetails);

        // 4. Retorna token
        return new AuthenticationResponse(token);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody RegisterRequest request) {
        // 1. Criptografa a senha
        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        // 2. Cria novo usuário
        Users newUser = new Users();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(encryptedPassword);
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setUsersRole(UsersRole.valueOf(request.getUsersRole())); // Ex: ADMIN, USER

        // 3. Salva no banco
        usersRepository.save(newUser);

        // 4. Gera token
        UserDetails userDetails = userDetailsService.loadUserByUsername(newUser.getUsername());
        String token = jwtService.generateToken(userDetails);

        // 5. Retorna token
        return new AuthenticationResponse(token);
    }

}