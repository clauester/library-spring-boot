package com.example.biblioteca.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.persistance.entities.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer>{
    
}
