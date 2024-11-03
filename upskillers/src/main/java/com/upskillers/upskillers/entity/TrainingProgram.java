package com.upskillers.upskillers.entity;

import java.time.LocalDate;
import java.util.List;

import com.upskillers.upskillers.enums.ProgramStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingProgram {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Prerequisite is required")
    private String prerequisite;

    @Column(name = "min_capacity") @NotNull(message = "Min Capacity is required")
    private Integer minCapacity;

    @Column(name = "max_capacity") @NotNull(message = "Max Capacity is required")
    private Integer maxCapacity;

    @Column(name = "start_date") @NotNull(message = "Start date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate starDate;

    @Column(name = "end_date") @NotNull(message = "End date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull(message = "Program Status is required(SCHEDULED, INPROGRESS, COMPLETED, CANCELED)")
    private ProgramStatus status;

    @OneToOne
    private Trainer trainer;

    @OneToMany(mappedBy = "trainingProgram", cascade = CascadeType.ALL)
    private List<Learner> learners;

    @Override
    public String toString(){
        return String.format("Program[id: %d, title: %s, status: %s]", id, title, status.toString());
    }
}