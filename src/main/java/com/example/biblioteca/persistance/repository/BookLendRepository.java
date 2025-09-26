package com.example.biblioteca.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.persistance.entities.BookLendEntity;

public interface BookLendRepository extends JpaRepository<BookLendEntity, Integer> {
    
}
