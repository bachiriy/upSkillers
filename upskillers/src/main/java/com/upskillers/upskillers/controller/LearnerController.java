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

import com.upskillers.upskillers.dto.LearnerDto;
import com.upskillers.upskillers.entity.Learner;

import com.upskillers.upskillers.exceptions.ResponseCustom;
import com.upskillers.upskillers.service.LearnerService;


@RestController
@RequestMapping("/api")
public class LearnerController {
    @Autowired private LearnerService learnerService;

    @GetMapping("/learners")
    public List<LearnerDto> getLearners() {
        return learnerService.getAllDto();
    }

    @GetMapping("/learners/{id}")
    public Learner getLearner(@Valid @PathVariable("id") Long id) {
        return learnerService.get(id);
    }

    @PostMapping("/learners")
    public ResponseCustom postLearner(@Valid @ModelAttribute Learner learner) {
        return learnerService.add(learner);
    }


    @PutMapping("/learners/{id}")
    public ResponseCustom putLearner(@ModelAttribute Learner learner) {
        return learnerService.update(learner);
    }

    @DeleteMapping("/learners/{id}")
    public ResponseCustom deleteLearner(@PathVariable("id") Long id) {
        return learnerService.delete(id);
    }
}