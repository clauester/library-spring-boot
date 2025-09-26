package com.example.biblioteca.web.dtos.responseDTO;

import java.util.Set;

import com.example.biblioteca.persistance.entities.RolEntity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

public class UserResponseDTO {

    private String username;

    private String email;

    private Set<RolEntity> rol;
}
