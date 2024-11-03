package com.upskillers.upskillers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upskillers.upskillers.entity.TrainingProgram;

public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, Long> {
    TrainingProgram findByTitle(String title);
}
