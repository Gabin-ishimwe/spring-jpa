package com.gabinishimwe.springdatajpa.repositories;

import com.gabinishimwe.springdatajpa.entities.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {
}
