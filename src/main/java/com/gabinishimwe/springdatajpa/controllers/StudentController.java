package com.gabinishimwe.springdatajpa.controllers;

import com.gabinishimwe.springdatajpa.entities.Student;
import com.gabinishimwe.springdatajpa.entities.StudentDto;
import com.gabinishimwe.springdatajpa.error.StudentServiceException;
import com.gabinishimwe.springdatajpa.services.CloudinaryService;
import com.gabinishimwe.springdatajpa.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/student")
public class StudentController {
    private final StudentService studentService;

    private final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
//    @PostMapping
//    public void createStudent(@Valid @RequestPart Student student, @RequestPart @Valid @NotNull @NotBlank MultipartFile image) throws IOException {
//        studentService.createStudent(student);
//    }

    @PostMapping
    public void createStudent(@ModelAttribute StudentDto studentDto) throws IOException {
        studentService.createStudent(studentDto);
    }

    @GetMapping
    public List<Student> findAllStudents() {
        LOGGER.info("INSIDE THE STUDENT CONTROLLER------");
        return studentService.findAllStudents();
    }

    @GetMapping(path = "{firstName}")
    public List<Student> findByFirstName(@PathVariable("firstName") String firstName) {
//        return studentService.findByFirstName(firstName);
        return studentService.findByFirstNameContaining(firstName);
//        return studentService.findByGuardianName(firstName);
    }
    @PutMapping(path = "{id}")
    public Student updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        return studentService.updateStudent(student, id);
    }

    @GetMapping(path = "one/{id}")
    public Student findOneStudent(@PathVariable Long id) throws StudentServiceException {
        return studentService.findStudent(id);
    }
}
