package com.gabinishimwe.springdatajpa.converter;

import com.gabinishimwe.springdatajpa.entities.Course;
import com.gabinishimwe.springdatajpa.entities.CourseDto;
import com.gabinishimwe.springdatajpa.entities.Teacher;
import org.springframework.stereotype.Component;


@Component
public class CourseConverter {
    public Course dtoToEntity(CourseDto courseDto, Teacher teacher) {
        Course course = new Course();
        course.setTitle(courseDto.getTitle());
        course.setCredits(courseDto.getCredits());
        course.setTeacher(teacher);

        return course;
    }
}
