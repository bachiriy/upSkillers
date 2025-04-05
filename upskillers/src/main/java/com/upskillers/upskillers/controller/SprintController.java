package com.upskillers.upskillers.controller;

import java.util.List;

import javax.swing.Spring;
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
import com.upskillers.upskillers.entity.Sprint;
import com.upskillers.upskillers.entity.Trainer;
import com.upskillers.upskillers.exceptions.ResponseCustom;
import com.upskillers.upskillers.service.SprintService;
import com.upskillers.upskillers.service.TrainerService;

@RestController
@RequestMapping("/api/sprints")
public class SprintController {
    
 @Autowired private SprintService service;

    @GetMapping()
    public List<Sprint> getSprints() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Sprint getSprint(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @PostMapping()
    public ResponseCustom postSprint(@Valid @ModelAttribute Sprint sprint) {
        return service.add(sprint);
    }

    // @PutMapping("/{id}")
    // public ResponseCustom putTrainer(@ModelAttribute Trainer trainer) {
    //     return trainerService.update(trainer);
    // }

    // @DeleteMapping("/trainers/{id}")
    // public ResponseCustom deleteTrainer(@PathVariable("id") Long id) {
    //     return trainerService.delete(id);
    // }
}
