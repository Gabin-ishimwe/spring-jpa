package com.gabinishimwe.springdatajpa.controllers;

import com.gabinishimwe.springdatajpa.entities.Student;
import com.gabinishimwe.springdatajpa.entities.Teacher;
import com.gabinishimwe.springdatajpa.services.StudentService;
import com.gabinishimwe.springdatajpa.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public void createTeacher(@RequestBody Teacher teacher) {
        teacherService.createTeacher(teacher);
    }

    @GetMapping
    public List<Teacher> findAllTeacher() {
        return teacherService.findAllTeacher();
    }
}
