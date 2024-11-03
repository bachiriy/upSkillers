package com.upskillers.upskillers.service;

import com.upskillers.upskillers.dto.TrainingProgramDto;
import com.upskillers.upskillers.entity.TrainingProgram;
import com.upskillers.upskillers.exceptions.ElementAlreadyExistsException;
import com.upskillers.upskillers.exceptions.NoSuchElementExistsException;
import com.upskillers.upskillers.exceptions.ResponseCustom;
import com.upskillers.upskillers.repository.TrainingProgramRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class TrainingProgramService implements Service<TrainingProgram, Long> {

    
    
    @Autowired private TrainingProgramRepository trainingProgramRepository;
    @Autowired private ModelMapper modelMapper;

    public TrainingProgram get(Long id){
        return trainingProgramRepository.findById(id).orElseThrow(() -> new NoSuchElementExistsException("No training program foudn with Id" + id));
    }

    public ResponseCustom add(TrainingProgram trainingProgram){
        TrainingProgram existingProgram = trainingProgramRepository.findByTitle(trainingProgram.getTitle());
        if (existingProgram == null) {
            trainingProgramRepository.save(trainingProgram);
            return new ResponseCustom(HttpStatus.OK.value(), "Program created");
        } else throw new ElementAlreadyExistsException("Training Program already exists");
    }

    public ResponseCustom update(TrainingProgram trainingProgram){
        TrainingProgram existingProgram = trainingProgramRepository.findById(trainingProgram.getId()).get();
        if (existingProgram == null) {
            throw new NoSuchElementExistsException("No program found with Id " + trainingProgram.getId());
        } else {
            if(trainingProgram.getTitle() != null) existingProgram.setTitle(trainingProgram.getTitle());
            if(trainingProgram.getPrerequisite() != null) existingProgram.setPrerequisite(trainingProgram.getPrerequisite());
            if(trainingProgram.getMinCapacity() != null) existingProgram.setMinCapacity(trainingProgram.getMinCapacity());
            if(trainingProgram.getMaxCapacity() != null) existingProgram.setMaxCapacity(trainingProgram.getMaxCapacity());
            if(trainingProgram.getStarDate() != null) existingProgram.setStarDate(trainingProgram.getStarDate());
            if(trainingProgram.getEndDate() != null) existingProgram.setEndDate(trainingProgram.getEndDate());
            if(trainingProgram.getStatus() != null) existingProgram.setStatus(trainingProgram.getStatus());
            if(trainingProgram.getTrainer() != null) existingProgram.setTrainer(trainingProgram.getTrainer());
            if(trainingProgram.getLearners() != null) {
                trainingProgram.getLearners().forEach(l -> l.setTrainingProgram(existingProgram));
                existingProgram.setLearners(trainingProgram.getLearners());
            }

            trainingProgramRepository.save(existingProgram);
            return new ResponseCustom(HttpStatus.OK.value(), "Program updated");
        }
    }


    public ResponseCustom delete(Long id){
        TrainingProgram existingProgram = trainingProgramRepository.findById(id).get();
        if (existingProgram != null) {
            trainingProgramRepository.delete(existingProgram);
            return new ResponseCustom(HttpStatus.OK.value(), "Program deleted");
        } else throw new NoSuchElementExistsException("Program not found with Id" + id);
    }

    public List<TrainingProgram> getAll(){
        return null; // why? cz impl 
    }

    public List<TrainingProgramDto> getAllDto(){
        if (this.modelMapper.getTypeMap(TrainingProgram.class, TrainingProgramDto.class) == null) {
            TypeMap<TrainingProgram, TrainingProgramDto> propretyMaper = this.modelMapper.createTypeMap(TrainingProgram.class, TrainingProgramDto.class);
            propretyMaper.addMappings(
                mapper -> mapper.map(src -> src.getLearners(), TrainingProgramDto::setLearners)
            );
            propretyMaper.addMappings(
                mapper -> mapper.map(src -> src.getTrainer().toString(), TrainingProgramDto::setTrainer)
            );
        }
        return trainingProgramRepository.findAll().stream().map(t -> this.modelMapper.map(t, TrainingProgramDto.class)).collect(Collectors.toList());
    }
}