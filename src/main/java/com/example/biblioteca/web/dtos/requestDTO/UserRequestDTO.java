package com.example.biblioteca.web.dtos.requestDTO;

import java.util.Set;

import com.example.biblioteca.persistance.entities.RolEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    
    private Integer id;

    private String username;

    private String password;

    private String email;

    private Set<RolEntity> roles;
}
