package com.upskillers.upskillers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Learner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First name is required")
    private String firstName;

    @NotNull(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Level is required")
    private String level;

    @ManyToOne(fetch = FetchType.LAZY)
    private TrainingProgram trainingProgram;

    @OneToOne
    private Classe classe;
}
