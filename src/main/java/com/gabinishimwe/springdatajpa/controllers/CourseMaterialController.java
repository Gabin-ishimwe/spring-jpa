package com.gabinishimwe.springdatajpa.controllers;

import com.gabinishimwe.springdatajpa.entities.Course;
import com.gabinishimwe.springdatajpa.entities.CourseMaterial;
import com.gabinishimwe.springdatajpa.services.CourseMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/courseMaterial")
public class CourseMaterialController {
    private final CourseMaterialService courseMaterialService;

    @Autowired
    CourseMaterialController(CourseMaterialService courseMaterialService) {
        this.courseMaterialService = courseMaterialService;
    }

    @PostMapping
    public void createCourseMaterial(@RequestBody CourseMaterial courseMaterial) {
        courseMaterialService.createCourseMaterial(courseMaterial);
    }

    @GetMapping
    public List<CourseMaterial> findAllCourseMaterial() {
        return courseMaterialService.findAllCourseMaterial();
    }
}
