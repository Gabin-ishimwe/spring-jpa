package com.gabinishimwe.springdatajpa.entities;

import lombok.Data;

@Data
public class PasswordModel {

    private String emailId;
    private String oldPassword;
    private String newPassword;
}
