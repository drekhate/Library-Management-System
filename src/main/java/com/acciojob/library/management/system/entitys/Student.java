package com.acciojob.library.management.system.entitys;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private String name;
    private int age;
    @Column(name = "contactNo", unique = true, nullable = false)
    private String mobNo;
    private String emailId;
    private String bloodGroup;
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private LibraryCard libraryCard;

}
