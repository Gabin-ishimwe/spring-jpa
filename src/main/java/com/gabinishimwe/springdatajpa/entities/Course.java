package com.gabinishimwe.springdatajpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "course_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long courseId;
    @Column(
            name = "course_title",
            nullable = false
    )
    private String title;
    @Column(
            name = "course_credits",
            nullable = false
    )
    private Integer credits;

    @OneToOne(
            mappedBy = "course"
    )
    @JsonIgnore
    private CourseMaterial courseMaterial;

    @ManyToOne(
            cascade = CascadeType.ALL,
            optional = false
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    // Not well implemented !!!!
//    @ManyToMany
//    @JoinTable(
//            name = "student_course_map",
//            joinColumns = @JoinColumn(
//                    name = "course_id",
//                    referencedColumnName = "courseId"
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "student_id",
//                    referencedColumnName = "studentId"
//            )
//    )
    private List<Student> students;
}
