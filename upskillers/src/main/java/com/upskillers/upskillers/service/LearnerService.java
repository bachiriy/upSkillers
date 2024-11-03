package com.upskillers.upskillers.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.upskillers.upskillers.dto.LearnerDto;
import com.upskillers.upskillers.entity.Learner;
import com.upskillers.upskillers.exceptions.ElementAlreadyExistsException;
import com.upskillers.upskillers.exceptions.NoSuchElementExistsException;
import com.upskillers.upskillers.exceptions.ResponseCustom;
import com.upskillers.upskillers.repository.LearnerRepository;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class LearnerService implements Service<Learner, Long> {

    @Autowired private LearnerRepository learnerRepository;
    @Autowired private ModelMapper modelMapper;


    public Learner get(Long id){
        return learnerRepository.findById(id).orElseThrow(() -> new NoSuchElementExistsException("No learner found with id " + id));
    }

    public ResponseCustom add(Learner learner){
        int existingLearners = learnerRepository.findByFirstNameAndLastName(learner.getFirstName(), learner.getLastName()).size();
        if (existingLearners == 0) {
            learnerRepository.save(learner);
            return new ResponseCustom(HttpStatus.CREATED.value(), "Learner created successfully.");
        } else throw new ElementAlreadyExistsException("Learner already exists.");
    }
    public ResponseCustom update(Learner learner){
        Learner existingLearner = learnerRepository.findById(learner.getId()).orElse(null);
        if (existingLearner == null) {
            throw new NoSuchElementExistsException("No learner found with the id " + learner.getId());
        } else {
            if (learner.getFirstName() != null) existingLearner.setFirstName(learner.getFirstName());
            if (learner.getLastName() != null) existingLearner.setLastName(learner.getLastName());
            if (learner.getEmail() != null) existingLearner.setEmail(learner.getEmail());
            if (learner.getLevel() != null) existingLearner.setLevel(learner.getLevel());
            if (learner.getClasse() != null) existingLearner.setClasse(learner.getClasse());
            if (learner.getTrainingProgram() != null) existingLearner.setTrainingProgram(learner.getTrainingProgram());

            learnerRepository.save(existingLearner);
            return new ResponseCustom(HttpStatus.OK.value(), "Learner updated successfully.");
        }
    }
    public  ResponseCustom delete(Long id){
        Learner existingLearner = learnerRepository.findById(id).orElse(null);
        if (existingLearner == null) {
            throw new NoSuchElementExistsException("No learner found with the id " + id);
        } else {
            learnerRepository.delete(existingLearner);
            return new ResponseCustom(HttpStatus.CREATED.value(), "Learner deleted successfully.");
        }
    }
    public List<Learner> getAll(){return null;}

    public List<LearnerDto> getAllDto(){
        if (this.modelMapper.getTypeMap(Learner.class, LearnerDto.class) == null) {
            TypeMap<Learner, LearnerDto> propretyMaper = this.modelMapper.createTypeMap(Learner.class, LearnerDto.class);
            propretyMaper.addMappings(
                mapper -> mapper.map(src -> src.getTrainingProgram().toString(), LearnerDto::setTrainingProgram)
            );
        }
        return learnerRepository.findAll().stream().map(l -> this.modelMapper.map(l, LearnerDto.class)).collect(Collectors.toList());
    }
}
