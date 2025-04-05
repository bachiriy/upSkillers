package com.upskillers.upskillers.service;

import com.upskillers.upskillers.dto.TrainerDto;
import com.upskillers.upskillers.entity.Sprint;
import com.upskillers.upskillers.entity.Trainer;
import com.upskillers.upskillers.exceptions.ElementAlreadyExistsException;
import com.upskillers.upskillers.exceptions.NoSuchElementExistsException;
import com.upskillers.upskillers.exceptions.ResponseCustom;
import com.upskillers.upskillers.exceptions.SprintInvalidException;
import com.upskillers.upskillers.repository.SprintRepository;
import com.upskillers.upskillers.repository.TrainerRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;
@org.springframework.stereotype.Service
public class SprintService implements Service<Sprint, Long>{

    @Autowired private SprintRepository repository;
    @Autowired private ModelMapper modelMapper;


    public Sprint get(Long id){
        return repository.findById(id).orElseThrow(() -> new SprintInvalidException("Sprint with Id " + id + " not found"));
    }

    public List<Sprint> getAll(){
        return repository.findAll();
    }
    public List<TrainerDto> getAllDto(){
       return null;
    }

    public ResponseCustom add(Sprint sprint){
        int  existingTrainer = repository.findByTitle(sprint.getTitle()).size();
        if (existingTrainer == 0) {
            repository.save(sprint);
            return new ResponseCustom(HttpStatus.CREATED.value(), "Sprint created succussfully");
        } else {
            throw new SprintInvalidException("Sprint with this informations already exists");
        }
    }

    public ResponseCustom update(Trainer trainer){
        return new ResponseCustom();
    }

    public ResponseCustom delete(Long id){
        return new ResponseCustom();
    }
}