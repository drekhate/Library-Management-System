package com.acciojob.library.management.system.entitys;

import com.acciojob.library.management.system.enums.CardStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
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
@Table(name = "libraryCard")
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardNo;
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;
    private String nameOnCard;
    private Integer noOfBookIssued;
    @OneToOne
    @JoinColumn
    private Student student;
    @OneToMany(mappedBy = "libraryCard", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> transactionList = new ArrayList<>();
}
