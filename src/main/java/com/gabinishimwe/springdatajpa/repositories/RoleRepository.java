package com.gabinishimwe.springdatajpa.repositories;

import com.gabinishimwe.springdatajpa.entities.Role;
import com.gabinishimwe.springdatajpa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
