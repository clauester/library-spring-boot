package com.example.biblioteca.persistance.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book_lend")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookLendEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_lend_id")
    private Integer bookLendId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(columnDefinition = "TIME")
    private LocalDateTime lendDate;
    
    @Column(name = "return_date",columnDefinition = "TIME")
    private LocalDateTime returnDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "lend", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookEntity> books;


}
