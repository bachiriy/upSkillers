package com.upskillers.upskillers.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upskillers.upskillers.entity.Sprint;

public interface SprintRepository extends JpaRepository<Sprint, Long> {
    
    List<Sprint> findByTitle(String title);
}