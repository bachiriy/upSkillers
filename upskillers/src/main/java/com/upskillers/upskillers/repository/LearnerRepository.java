package com.upskillers.upskillers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upskillers.upskillers.entity.Learner;
import java.util.List;


public interface LearnerRepository extends JpaRepository<Learner, Long>{
    List<Learner> findByFirstNameAndLastName(String firstName, String lastName);
}