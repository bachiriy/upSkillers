package com.upskillers.upskillers.service;

import com.upskillers.upskillers.dto.TrainerDto;
import com.upskillers.upskillers.entity.Trainer;
import com.upskillers.upskillers.exceptions.ElementAlreadyExistsException;
import com.upskillers.upskillers.exceptions.NoSuchElementExistsException;
import com.upskillers.upskillers.exceptions.ResponseCustom;
import com.upskillers.upskillers.repository.TrainerRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;
@org.springframework.stereotype.Service
public class TrainerService implements Service<Trainer, Long>{

    @Autowired private TrainerRepository trainerRepository;
    @Autowired private ModelMapper modelMapper;


    public Trainer get(Long id){
        return trainerRepository.findById(id).orElseThrow(() -> new NoSuchElementExistsException("Trainer with Id " + id + " not found"));
    }

    public List<Trainer> getAll(){
        return null;
    }
    public List<TrainerDto> getAllDto(){
          if (this.modelMapper.getTypeMap(Trainer.class, TrainerDto.class) == null) {
            TypeMap<Trainer, TrainerDto> propretyMaper = this.modelMapper.createTypeMap(Trainer.class, TrainerDto.class);
            propretyMaper.addMappings(
                mapper -> mapper.map(src -> src.getTrainingProgram().toString(), TrainerDto::setTrainingProgram)  
            );
        }
        return trainerRepository.findAll().stream().map(trainer -> modelMapper.map(trainer, TrainerDto.class)).collect(Collectors.toList());
    }

    public ResponseCustom add(Trainer trainer){
        Trainer existingTrainer = trainerRepository.findByFirstNameAndLastNameAndEmail(trainer.getFirstName(), trainer.getLastName(), trainer.getEmail());
        if (existingTrainer != null) {
            throw new ElementAlreadyExistsException("Trainer with this informations already exists");
        } else {
            trainerRepository.save(trainer);
            return new ResponseCustom(HttpStatus.CREATED.value(), "Trainer created succussfully");
        }
    }

    public ResponseCustom update(Trainer trainer){
        Trainer existingTrainer = trainerRepository.findById(trainer.getId()).get();
        if (existingTrainer == null) {
            throw new NoSuchElementExistsException("No such trainer found with Id " + trainer.getId());
        } else {
            if(trainer.getFirstName() != null) existingTrainer.setFirstName(trainer.getFirstName());
            if(trainer.getLastName() != null) existingTrainer.setLastName(trainer.getLastName());
            if(trainer.getEmail() != null) existingTrainer.setEmail(trainer.getEmail());
            if(trainer.getSpecialty() != null) existingTrainer.setSpecialty(trainer.getSpecialty());
            if(trainer.getTrainingProgram() != null) existingTrainer.setTrainingProgram(trainer.getTrainingProgram());
            if(trainer.getClasse() != null) existingTrainer.setClasse(trainer.getClasse());

            trainerRepository.save(existingTrainer);
            return new ResponseCustom(HttpStatus.OK.value(), "Trainer updated succussfully");
        }
    }

    public ResponseCustom delete(Long id){
        Trainer existingTrainer = trainerRepository.findById(id).get();
        if (existingTrainer != null) {
            trainerRepository.delete(existingTrainer);
            return new ResponseCustom(HttpStatus.OK.value(), "Trainer deleted");
        } else {
            throw new NoSuchElementExistsException("No trainer found with the Id " + id);
        }
    }
}