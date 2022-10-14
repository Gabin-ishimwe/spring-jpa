package com.gabinishimwe.springdatajpa.repositories;

import com.gabinishimwe.springdatajpa.entities.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
