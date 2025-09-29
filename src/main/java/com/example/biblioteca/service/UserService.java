package com.example.biblioteca.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.biblioteca.persistance.entities.RolEntity;
import com.example.biblioteca.persistance.entities.UserEntity;
import com.example.biblioteca.persistance.repository.RolRepository;
import com.example.biblioteca.persistance.repository.UserRepository;
import com.example.biblioteca.service.mapper.UserMapper;
import com.example.biblioteca.web.dtos.requestDTO.CreateUserRequestDTO;
import com.example.biblioteca.web.dtos.responseDTO.UserResponseDTO;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final RolRepository rolRepository;

    private UserService(
            UserRepository userRepository, 
            UserMapper mapper,
            RolRepository rolRepository
            ){
                
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.rolRepository = rolRepository;
    }


    public List<UserResponseDTO> getAll(){
        return this.userRepository
        .findAll()
        .stream()
        .map(entity -> mapper.toDto(entity))
        .collect(Collectors.toList());
    }

    public UserResponseDTO add(CreateUserRequestDTO user){

        Set<RolEntity> roles = user.getRoles().stream()
            .map(rol -> rolRepository.findByName(rol.getName())
                .orElseThrow(() -> new RuntimeException("rol no encontrado")))
            .collect(Collectors.toSet());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        UserEntity newUser = this.userRepository.save(mapper.toEntity(user));
        return mapper.toDto(newUser);
    }

    public UserResponseDTO update(CreateUserRequestDTO user){
        Set<RolEntity> roles = user.getRoles().stream()
            .map(rol -> rolRepository.findByName(rol.getName())
                .orElseThrow(() -> new RuntimeException("rol no encontrado")))
            .collect(Collectors.toSet());

        UserEntity founded = userRepository.findById(user.getUserId())
            .orElseThrow(() -> new RuntimeException("usuario no encontrado"));

        founded.setEmail(user.getEmail());
        founded.setPassword(user.getPassword());
        founded.setRoles(roles);
        founded.setUsername(user.getUsername());

        return mapper.toDto(userRepository.save(founded));

    }
    
}
