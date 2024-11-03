package com.upskillers.upskillers.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upskillers.upskillers.dto.TrainingProgramDto;
import com.upskillers.upskillers.entity.TrainingProgram;
import com.upskillers.upskillers.exceptions.ResponseCustom;
import com.upskillers.upskillers.service.TrainingProgramService;



@RestController
@RequestMapping("/api")
public class TrainingProgramController {
    @Autowired private TrainingProgramService trainingProgramService;

    @GetMapping("/programs")
    public List<TrainingProgramDto> getTrainingPrograms() {
        return trainingProgramService.getAllDto();
    }

    @GetMapping("/programs/{id}")
    public TrainingProgram getTrainingProgram(@PathVariable("id") Long id) {
        return trainingProgramService.get(id);
    }

    @PostMapping("/programs")
    public ResponseCustom postTrainingProgram(@Valid @ModelAttribute TrainingProgram trainingProgram) {
        return trainingProgramService.add(trainingProgram);
    }

    @PutMapping("/programs/{id}")
    public ResponseCustom putTrainingProgram(@ModelAttribute TrainingProgram trainingProgram) {
        return trainingProgramService.update(trainingProgram);
    }

    @DeleteMapping("/programs/{id}")
    public ResponseCustom deleteTrainingProgram(@PathVariable("id") Long id) {
        return trainingProgramService.delete(id);
    }
}
