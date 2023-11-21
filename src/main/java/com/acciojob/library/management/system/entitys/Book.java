package com.acciojob.library.management.system.entitys;

import com.acciojob.library.management.system.enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private String bookName;
    private int price;
    private int noOfPages;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    private double rating;
    private boolean isAvailable;
    @ManyToOne
    @JoinColumn
    private Author author;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();

}