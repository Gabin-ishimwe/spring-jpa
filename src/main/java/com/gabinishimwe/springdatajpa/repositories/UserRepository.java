package com.gabinishimwe.springdatajpa.repositories;

import com.gabinishimwe.springdatajpa.entities.PasswordModel;
import com.gabinishimwe.springdatajpa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
