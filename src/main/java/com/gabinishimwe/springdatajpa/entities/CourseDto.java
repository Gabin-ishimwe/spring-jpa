package com.gabinishimwe.springdatajpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private String title;
    private int credits;
    private Long teacherId;
}
