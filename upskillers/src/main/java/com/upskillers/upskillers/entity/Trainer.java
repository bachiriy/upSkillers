package com.upskillers.upskillers.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "First name is required")
    private String firstName;

    @NotNull(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "specialty is required")
    private String specialty;

    @OneToOne
    private TrainingProgram trainingProgram;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "classe_id")
    private Classe classe;

    @Override
    public String toString(){
        return String.format("Trainer[id: %d, firstName: %s, lastName: %s , email: %s]", id, firstName, lastName, email);
    }
}
