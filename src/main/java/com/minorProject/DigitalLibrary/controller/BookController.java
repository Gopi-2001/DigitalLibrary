package com.minorProject.DigitalLibrary.controller;

import com.minorProject.DigitalLibrary.dto.createBookRequest;
import com.minorProject.DigitalLibrary.enums.FilerBookBy;
import com.minorProject.DigitalLibrary.enums.Operator;
import com.minorProject.DigitalLibrary.model.Book;
import com.minorProject.DigitalLibrary.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    Logger logger = LoggerFactory.getLogger("BookController.class");

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody createBookRequest createBookRequest){
        logger.info("Creating Book");
        return ResponseEntity.ok(bookService.createBook(createBookRequest));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id){
        logger.info("Fetching Book by Id" + id);
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAllBooks(){
        logger.info("Fetching all books");
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody createBookRequest createBookRequest){
        logger.info("updating book");
        return ResponseEntity.ok(bookService.updateBook(id, createBookRequest));
    }

    @GetMapping("/filter/{operator}/{filterBookBy}/{value}")
    public ResponseEntity<List<Book>> filterBook(@PathVariable Operator operator, @PathVariable FilerBookBy filterBookBy,@PathVariable String value){
       return ResponseEntity.ok(bookService.filterBook(operator,filterBookBy,value));
    }

}
