package com.gabinishimwe.springdatajpa.services;

import com.gabinishimwe.springdatajpa.entities.Course;
import com.gabinishimwe.springdatajpa.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void createCourse(Course course) {
        courseRepository.save(course);
    }

    public List<Course> findAllCourse() {
        return courseRepository.findAll();
    }

    public List<Course> findAllPageable(int page, int size) {
        PageRequest pagination = PageRequest.of(page, size);
        return courseRepository.findAll(pagination).getContent();
    }

    public List<Course> findAllSorting(int page, int size) {
        PageRequest sortingByName = PageRequest.of(page, size, Sort.by("title"));
        PageRequest sortingByNameDescending = PageRequest.of(page, size, Sort.by("title").descending());
        PageRequest sortingByNameAndCreditsDescending = PageRequest.of(page, size, Sort.by("title").descending().and(Sort.by("credits")));
//        return courseRepository.findAll(sortingByNameDescending).getContent();
        return courseRepository.findByTitleContaining("sys", sortingByName);
    }
}
