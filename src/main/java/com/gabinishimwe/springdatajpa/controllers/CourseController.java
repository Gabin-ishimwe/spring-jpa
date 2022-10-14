package com.gabinishimwe.springdatajpa.controllers;

import com.gabinishimwe.springdatajpa.entities.Course;
import com.gabinishimwe.springdatajpa.entities.CourseDto;
import com.gabinishimwe.springdatajpa.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public void createCourse(@RequestBody CourseDto courseDto) {
//        System.out.println(course);
        courseService.createCourse(courseDto);
    }

    @GetMapping
    public List<Course> getAllCourse() {
        return courseService.findAllCourse();
    }

    @GetMapping(path ="{pageNum}/{size}" )
    public List<Course> getByPagination(@PathVariable("pageNum") int page, @PathVariable("size") int size) {
//        return courseService.findAllPageable(page, size);
        return courseService.findAllSorting(page, size);
    }




}
