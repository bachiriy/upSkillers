package com.upskillers.upskillers.controller;

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

import com.upskillers.upskillers.dto.TrainerDto;
import com.upskillers.upskillers.entity.Trainer;
import com.upskillers.upskillers.exceptions.ResponseCustom;
import com.upskillers.upskillers.service.TrainerService;

import java.util.List;


@RestController
@RequestMapping("/api")
public class TrainerController {
    @Autowired private TrainerService trainerService;

    @GetMapping("/trainers")
    public List<TrainerDto> getTrainers() {
        return trainerService.getAllDto();
    }

    @GetMapping("/trainers/{id}")
    public Trainer getTrainer(@PathVariable("id") Long id) {
        return trainerService.get(id);
    }

    @PostMapping("/trainers")
    public ResponseCustom postTrainer(@Valid @ModelAttribute Trainer trainer) {
        return trainerService.add(trainer);
    }

    @PutMapping("/trainers/{id}")
    public ResponseCustom putTrainer(@ModelAttribute Trainer trainer) {
        return trainerService.update(trainer);
    }

    @DeleteMapping("/trainers/{id}")
    public ResponseCustom deleteTrainer(@PathVariable("id") Long id) {
        return trainerService.delete(id);
    }
}
