package com.example.biblioteca.persistance.entities;

import java.time.LocalDate;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer BookId;

    @Column(nullable = false)
    private String title;

    private String author;

    @Column(name = "publish_year",columnDefinition = "DATE")
    private LocalDate publishYear;

    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private Character status;

    @ManyToOne
    @JoinColumn(name = "book_lend_id") 
    private BookLendEntity lend;
}
