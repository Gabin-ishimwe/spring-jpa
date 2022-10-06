package com.gabinishimwe.springdatajpa.repositories;

import com.gabinishimwe.springdatajpa.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("gabin@gmail.com")
                .fName("gabin")
                .lName("ishimwe")
                .guardianEmail("tm@gmail.com")
                .guardianName("maman")
                .guardianMobile("-07834234232")
                .build();

        studentRepository.save(student);
    }

}