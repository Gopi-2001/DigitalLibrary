package com.minorProject.DigitalLibrary.repository;

import com.minorProject.DigitalLibrary.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByEmail(String value);

    List<Student> findByName(String value);

}
