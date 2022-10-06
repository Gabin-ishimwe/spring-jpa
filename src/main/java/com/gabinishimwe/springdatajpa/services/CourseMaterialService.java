package com.gabinishimwe.springdatajpa.services;

import com.gabinishimwe.springdatajpa.entities.Course;
import com.gabinishimwe.springdatajpa.entities.CourseMaterial;
import com.gabinishimwe.springdatajpa.repositories.CourseMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseMaterialService {
    private final CourseMaterialRepository courseMaterialRepository;

    @Autowired
    CourseMaterialService(CourseMaterialRepository courseMaterialRepository) {
        this.courseMaterialRepository = courseMaterialRepository;
    }

    public void createCourseMaterial(CourseMaterial courseMaterial) {
        courseMaterialRepository.save(courseMaterial);
    }

    public List<CourseMaterial> findAllCourseMaterial() {
        return courseMaterialRepository.findAll();
    }
}
