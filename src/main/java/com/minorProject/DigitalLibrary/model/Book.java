package com.minorProject.DigitalLibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minorProject.DigitalLibrary.enums.BookType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bookName;

    private int pages;

    private int cost;

    @Enumerated(value = EnumType.STRING)
    private BookType bookType;

    private String bookNo;

    private String producer;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnoreProperties({"bookList"})
    private Author author;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "book")
    private List<Txn> txnList;





}
