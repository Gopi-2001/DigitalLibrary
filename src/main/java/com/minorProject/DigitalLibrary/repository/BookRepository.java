package com.minorProject.DigitalLibrary.repository;

import com.minorProject.DigitalLibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByBookName(String value);

    List<Book> findByBookNo(String value);

    List<Book> findByCost(int value);

    List<Book> findByPages(int value);

    List<Book> findByCostGreaterThan(int i);

    List<Book> findByPagesGreaterThan(int i);

    List<Book> findByCostLessThan(int i);

    List<Book> findByPagesLessThan(int i);
}
