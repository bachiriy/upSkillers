package com.upskillers.upskillers.dto;

import com.upskillers.upskillers.entity.Classe;

import lombok.Data;

@Data
public class LearnerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String level;
    private String trainingProgram;
    private Classe classe;
}
