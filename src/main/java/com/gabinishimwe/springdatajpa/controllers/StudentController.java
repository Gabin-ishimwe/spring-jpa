package com.gabinishimwe.springdatajpa.controllers;

import com.gabinishimwe.springdatajpa.entities.Student;
import com.gabinishimwe.springdatajpa.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public void createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
    }

    @GetMapping
    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping(path = "{firstName}")
    public List<Student> findByFirstName(@PathVariable("firstName") String firstName) {
//        return studentService.findByFirstName(firstName);
//        return studentService.findByFirstNameContaining(firstName);
        return studentService.findByGuardianName(firstName);
    }
    @PutMapping(path = "{id}")
    public Student updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        return studentService.updateStudent(student, id);
    }
}
