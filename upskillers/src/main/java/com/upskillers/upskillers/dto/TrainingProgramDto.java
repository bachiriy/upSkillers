package com.upskillers.upskillers.dto;

import java.time.LocalDate;
import java.util.List;
import com.upskillers.upskillers.enums.ProgramStatus;

import lombok.Data;

@Data
public class TrainingProgramDto {
    private Long id;
    private String title;
    private String prerequisite;
    private Integer minCapacity;
    private Integer maxCapacity;
    private LocalDate starDate;
    private LocalDate endDate;
    private ProgramStatus status;
    private String trainer;
    private List<LearnerDto> learners;
}
