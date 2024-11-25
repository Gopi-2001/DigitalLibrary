package com.minorProject.DigitalLibrary.service;

import com.minorProject.DigitalLibrary.model.Author;
import com.minorProject.DigitalLibrary.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public Author getAuthor(int id) {
        return authorRepository.findById(id).orElse(null);
    }
}
