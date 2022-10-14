package com.gabinishimwe.springdatajpa.converter;


import com.gabinishimwe.springdatajpa.entities.Guardian;
import com.gabinishimwe.springdatajpa.entities.Student;
import com.gabinishimwe.springdatajpa.entities.StudentDto;
import com.gabinishimwe.springdatajpa.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StudentConverter {
    @Autowired
    private CloudinaryService cloudinaryService;

    public Student dtoToEntity(StudentDto studentDto) throws IOException {
        Student student = new Student();
        Guardian guardian = new Guardian();
        String imageUrl = cloudinaryService.uploadImage(studentDto.getImage());
        guardian.setName(studentDto.getGuardianName());
        guardian.setMobileNumber(studentDto.getGuardianNumber());
        guardian.setEmail(studentDto.getGuardianEmail());

        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmailId(studentDto.getEmail());
        student.setGuardian(guardian);
        student.setImageUrl(imageUrl);

        return student;

    }

    public StudentDto entityToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setEmail(student.getEmailId());
        studentDto.setGuardianName(student.getGuardian().getName());
        studentDto.setGuardianEmail(student.getGuardian().getMobileNumber());
        studentDto.setGuardianNumber(student.getGuardian().getMobileNumber());
        return studentDto;
    }
}
