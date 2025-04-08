package com.vamberto.School.models;

import com.vamberto.School.models.enums.UsersRole;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Users {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name= "username" , length = 255, nullable = false)
    private String username;

    @Column(name= "password" , length = 255)
    private String password;

    @Column(name= "email" , length = 255, nullable = false)
    private String  email;

    @Column(name= "name" , length =255 , nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name= "role" , length =20 , nullable = false)
    private UsersRole usersRole;
}
