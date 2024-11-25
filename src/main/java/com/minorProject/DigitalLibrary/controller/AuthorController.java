package com.minorProject.DigitalLibrary.controller;

import com.minorProject.DigitalLibrary.service.AuthorService;
import com.minorProject.DigitalLibrary.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    Logger logger = LoggerFactory.getLogger("AuthorController.class");

    @GetMapping("/get/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable int id){
        logger.info("Fetching Author by Id" + id);
        return ResponseEntity.ok(authorService.getAuthor(id));
    }


}
