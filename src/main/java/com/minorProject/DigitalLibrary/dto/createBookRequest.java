package com.minorProject.DigitalLibrary.dto;

import com.minorProject.DigitalLibrary.model.Author;
import com.minorProject.DigitalLibrary.model.Book;
import com.minorProject.DigitalLibrary.enums.BookType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class createBookRequest implements Serializable {
    private String bookName;
    private String bookNo;
    private String bookType;
    private int pages;
    private int cost;
    private String producer;

    private String authorName;
    private String authorEmail;

    public Book toBook(){
        return Book.builder()
                .bookName(this.bookName)
                .bookNo(this.bookNo)
                .bookType(BookType.valueOf(bookType))
                .pages(this.pages)
                .cost(this.cost)
                .producer(this.producer)
                .build();
    }

    public Author toAuthor(){
        return Author.builder()
                .authorName(this.authorName)
                .email(this.authorEmail)
                .build();
    }

//        Attributes:
//            ○ bookName(String): Name of the book.
//            ○ bookNo(String): Unique identifier for the book.
//            ○ bookType(String): Type of the book (EDUCATIONAL, TECHNICAL).
//            ○ pages(int): Number of pages in the book.
//            ○ cost(int): Cost of the book.
//            ○ authorName(String): Name of the author.
//            ○ authorEmail (String): Email of the author.
//        ● Methods:
//            ○ toBook(): Converts this DTO to a Book entity.
//            ○ toAuthor(): Converts this DTO to an Author entity.

}
