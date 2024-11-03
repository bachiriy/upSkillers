package com.upskillers.upskillers.dto;

import com.upskillers.upskillers.entity.Classe;

import lombok.Data;

@Data
public class TrainerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String specialty;
    private String trainingProgram;
    private Classe classe;
}
