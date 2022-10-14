package com.gabinishimwe.springdatajpa.services;

import com.gabinishimwe.springdatajpa.converter.StudentConverter;
import com.gabinishimwe.springdatajpa.entities.Student;
import com.gabinishimwe.springdatajpa.entities.StudentDto;
import com.gabinishimwe.springdatajpa.error.StudentServiceException;
import com.gabinishimwe.springdatajpa.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentConverter studentConverter;


    @Autowired
    StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public void createStudent(StudentDto studentDto) throws IOException {
        Student entityStudent = studentConverter.dtoToEntity(studentDto);
        studentRepository.save(entityStudent);
//        System.out.println(student);
//        studentRepository.save(student);
    }

    public List<Student> findAllStudents() {
        LOGGER.info("INSIDE THE FIND ALL SERVICE-----");
        return studentRepository.findAll();
    }

    public Student findStudent(Long id) throws StudentServiceException {
        Optional<Student> findStudent = studentRepository.findById(id);

        if(findStudent.isEmpty()) {
            throw new StudentServiceException("Student not found !!!");
        }
        return findStudent.get();

    }

    public List<Student> findByFirstName(String name) {
        return studentRepository.findByFirstName(name);
    }

    public List<Student> findByFirstNameContaining(String name) {
        return studentRepository.findByFirstNameContaining(name);
    }

    public List<Student> findByGuardianName(String name) {
        return studentRepository.findByGuardianName(name);
    }

    public Student updateStudent(Student student, Long id) {
        final Student findStudent = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Student id doesn't exist"));
        if(student.getFirstName().length()!= 0 && !Objects.equals(student.getFirstName(), findStudent.getFirstName())) {
            findStudent.setFirstName(student.getFirstName());
        }
        if(student.getLastName().length() != 0 && !Objects.equals(student.getLastName(), findStudent.getLastName())) {
            findStudent.setLastName(student.getLastName());
        }

        if(student.getEmailId().length() != 0 && !Objects.equals(student.getEmailId(), findStudent.getEmailId())) {
            findStudent.setEmailId(student.getEmailId());
        }

        if(student.getGuardian().getName().length() != 0) {
            findStudent.getGuardian().setName(student.getGuardian().getName());
        }

        if(student.getGuardian().getEmail().length() != 0) {
            findStudent.getGuardian().setEmail(student.getGuardian().getEmail());
        }

        if(student.getGuardian().getMobileNumber().length() != 0) {
            findStudent.getGuardian().setMobileNumber(student.getGuardian().getMobileNumber());
        }

        return findStudent;
    }
}
