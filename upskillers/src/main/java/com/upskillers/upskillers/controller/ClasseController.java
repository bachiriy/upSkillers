package com.upskillers.upskillers.controller;

import com.upskillers.upskillers.entity.Classe;
import com.upskillers.upskillers.exceptions.ResponseCustom;
import com.upskillers.upskillers.service.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ClasseController {

    @Autowired private ClasseService classeService;

    @GetMapping("/classes")
    public List<Classe> getClasses() {
        return classeService.getAll();
    }

    @GetMapping("/classes/{id}")
    public Classe getClass(@PathVariable("id") Long id) {
        return classeService.get(id);
    }

    @PostMapping("/classes")
    public ResponseCustom postClasses(@Valid @ModelAttribute Classe classe) {
        return classeService.add(classe);
    }

    @PutMapping("/classes/{id}")
    public ResponseCustom putClasses(@ModelAttribute Classe classe) {
        return classeService.update(classe);
    }

    @DeleteMapping("/classes/{id}")
    public ResponseCustom deleteClasses(@PathVariable("id") Long id) {
        return classeService.delete(id);
    }
}
