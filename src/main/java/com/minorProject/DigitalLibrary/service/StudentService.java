package com.minorProject.DigitalLibrary.service;

import com.minorProject.DigitalLibrary.dto.createStudentRequest;
import com.minorProject.DigitalLibrary.enums.FilterStudentBy;
import com.minorProject.DigitalLibrary.enums.Operator;
import com.minorProject.DigitalLibrary.model.Student;
import com.minorProject.DigitalLibrary.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(createStudentRequest createStudentRequest) {
        Student student = createStudentRequest.toStudent();
        return studentRepository.save(student);
    }

    public Student getStudent(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Student updateStudent(int id,createStudentRequest createStudentRequest) {
        Student student = studentRepository.findById(id).get();

        student.setName(createStudentRequest.getName());
        student.setEmail((createStudentRequest.getEmail()));
        student.setAddress(createStudentRequest.getAddress());

        return studentRepository.save(student);
    }

    public List<Student> filterStudent(Operator operator, FilterStudentBy filterStudentBy, String value) {
        switch (operator){
            case EQUALS: {
                switch(filterStudentBy){
                    case EMAIL :
                        return studentRepository.findByEmail(value);
                    case NAME:
                        return studentRepository.findByName(value);
                }
            }
            default:
                return new ArrayList<>();
        }
    }
}
