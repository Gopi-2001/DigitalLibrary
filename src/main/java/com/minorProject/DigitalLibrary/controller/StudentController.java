package com.minorProject.DigitalLibrary.controller;

import com.minorProject.DigitalLibrary.dto.createStudentRequest;
import com.minorProject.DigitalLibrary.enums.FilterStudentBy;
import com.minorProject.DigitalLibrary.enums.Operator;
import com.minorProject.DigitalLibrary.model.Student;
import com.minorProject.DigitalLibrary.service.StudentService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody createStudentRequest createStudentRequest){
        logger.info("Creating Student");
        return ResponseEntity.ok(studentService.createStudent(createStudentRequest));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id){
        logger.info("Fetching Student with Id : " + id);
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent(){
        logger.info("Fetching All Students");
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id,@RequestBody createStudentRequest createStudentRequest){
        logger.info("Updating Student with Id : " + id);
        return ResponseEntity.ok(studentService.updateStudent(id,createStudentRequest));
    }

    @GetMapping("/filter/{operator}/{filterStudentBy}/{value}")
    public ResponseEntity<List<Student>> filterStudent(@PathVariable Operator operator, @PathVariable FilterStudentBy filterStudentBy,@PathVariable String value){
       return ResponseEntity.ok(studentService.filterStudent(operator,filterStudentBy,value));
    }

}
