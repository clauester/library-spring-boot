package com.example.biblioteca.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.persistance.entities.RolEntity;

public interface RolRepository extends JpaRepository<RolEntity, Integer> {
    
}
