package com.example.biblioteca.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.biblioteca.persistance.entities.UserEntity;
import com.example.biblioteca.persistance.repository.UserRepository;
import com.example.biblioteca.service.mapper.UserMapper;
import com.example.biblioteca.web.dtos.requestDTO.UserRequestDTO;
import com.example.biblioteca.web.dtos.responseDTO.UserResponseDTO;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    private UserService(UserRepository userRepository, UserMapper mapper){
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    public List<UserResponseDTO> getAll(){
        return this.userRepository
        .findAll()
        .stream()
        .map(entity -> mapper.toDto(entity))
        .collect(Collectors.toList());
    }

    public UserResponseDTO add(UserRequestDTO user){
       UserEntity newUser = this.userRepository.save(mapper.toEntity(user));
       return mapper.toDto(newUser);
    }

    public UserResponseDTO update(UserRequestDTO user){
        UserEntity founded = userRepository.findById(user.getId())
            .orElseThrow(() -> new RuntimeException("usuario no encontrado"));

        founded.setEmail(user.getEmail());
        founded.setPassword(user.getPassword());
        founded.setRoles(user.getRoles());
        founded.setUsername(null);

        return mapper.toDto(founded);

    }
    
}
