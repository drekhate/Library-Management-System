package com.acciojob.library.management.system.entitys;

import com.acciojob.library.management.system.enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    private Integer bookId;
    private String bookName;
    private int price;
    private int noOfPages;
    private Genre genre;
    private double rating;
    @ManyToOne
    @JoinColumn
    private Author author;

}