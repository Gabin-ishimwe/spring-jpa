package com.gabinishimwe.springdatajpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private String firstName;
    private String lastName;
    private String email;
    private String guardianName;
    private String guardianEmail;
    private String guardianNumber;
    private MultipartFile image;
}
