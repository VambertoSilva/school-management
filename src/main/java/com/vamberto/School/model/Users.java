package com.vamberto.School.model;

import com.vamberto.School.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
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
    private Email  email;

    @Column(name= "name" , length =255 , nullable = false)
    private String name;

    @Column(name= "role" , length =20 , nullable = false)
    private Role role;

    @Column(name= "created_at")
    @CreatedDate
    private LocalDateTime  created_at;

    @Column(name= "updated_at")
    @LastModifiedDate
    private LocalDateTime updated_at;


    @Column(name= "created_by")
    private UUID created_by;

    @Column(name= "updated_by")
    private UUID updated_by;

}
