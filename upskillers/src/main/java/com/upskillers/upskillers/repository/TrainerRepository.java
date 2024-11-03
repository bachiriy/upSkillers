package com.upskillers.upskillers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upskillers.upskillers.entity.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    public Trainer findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);
}
