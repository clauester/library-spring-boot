package com.example.biblioteca.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.example.biblioteca.persistance.entities.UserEntity;
import com.example.biblioteca.web.dtos.requestDTO.CreateUserRequestDTO;
import com.example.biblioteca.web.dtos.responseDTO.UserResponseDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toDto(UserEntity user);
    List<CreateUserRequestDTO> toDtos(List<UserEntity> users);
    UserEntity toEntity(CreateUserRequestDTO dto);

    
}
