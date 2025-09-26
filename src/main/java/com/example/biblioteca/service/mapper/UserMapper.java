package com.example.biblioteca.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.example.biblioteca.persistance.entities.UserEntity;
import com.example.biblioteca.web.dtos.requestDTO.UserRequestDTO;
import com.example.biblioteca.web.dtos.responseDTO.UserResponseDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toDto(UserEntity user);
    List<UserRequestDTO> toDtos(List<UserEntity> users);
    UserEntity toEntity(UserRequestDTO dto);

    
}
