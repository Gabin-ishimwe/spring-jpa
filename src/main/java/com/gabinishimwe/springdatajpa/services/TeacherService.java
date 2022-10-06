package com.gabinishimwe.springdatajpa.services;

import com.gabinishimwe.springdatajpa.entities.Teacher;
import com.gabinishimwe.springdatajpa.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    @Autowired
    TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void createTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public List<Teacher> findAllTeacher() {
        return teacherRepository.findAll();
    }
}
