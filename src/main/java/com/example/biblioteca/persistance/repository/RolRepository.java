package com.example.biblioteca.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.persistance.entities.RolEntity;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface RolRepository extends JpaRepository<RolEntity, Integer> {

    Optional<RolEntity> findByName(String name);
}
