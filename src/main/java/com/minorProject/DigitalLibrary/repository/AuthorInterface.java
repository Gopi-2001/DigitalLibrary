package com.minorProject.DigitalLibrary.repository;

import com.minorProject.DigitalLibrary.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorInterface extends JpaRepository<Author,Integer> {

}
