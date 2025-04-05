package com.upskillers.upskillers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "startDate is required.")
    private LocalDateTime startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "endDate is required.")
    private LocalDateTime endDate;
    
    @NotNull(message = "title is required.")
    private String title;

    @NotNull(message =  "formation is required.")
    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
    private List<TrainingProgram> formations;
}
