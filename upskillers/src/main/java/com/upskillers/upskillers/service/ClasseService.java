package com.upskillers.upskillers.service;

import com.upskillers.upskillers.entity.Classe;
import com.upskillers.upskillers.exceptions.ElementAlreadyExistsException;
import com.upskillers.upskillers.exceptions.NoSuchElementExistsException;
import com.upskillers.upskillers.exceptions.ResponseCustom;
import com.upskillers.upskillers.repository.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.List;

@org.springframework.stereotype.Service
public class ClasseService implements Service<Classe, Long> {

    @Autowired
    private ClasseRepository classeRepository;

    @Override
    public List<Classe> getAll() {
        return classeRepository.findAll();
    }

    @Override
    public Classe get(Long id) {
        return classeRepository.findById(id).orElseThrow(() -> new NoSuchElementExistsException("No classe present with id " + id));
    }

    @Override
    public ResponseCustom add(Classe classe) {
        Classe existingClasse = classeRepository.findByName(classe.getName());
        if (existingClasse == null) {
            classeRepository.save(classe);
            return new ResponseCustom(HttpStatus.CREATED.value(), "Classe created successfully");
        } else throw new ElementAlreadyExistsException("Classe already exists");
    }

    @Override
    public ResponseCustom update(Classe classe) {
        Classe existingClasse = classeRepository.findById(classe.getId()).orElse(null);
        if (existingClasse == null) {
            throw new NoSuchElementExistsException("No classe present with id " + classe.getId());
        } else {
            existingClasse.setName(classe.getName());
            existingClasse.setRoomNumber(classe.getRoomNumber());
            classeRepository.save(existingClasse);
            return new ResponseCustom(HttpStatus.OK.value(), "Classe updated successfully");
        }
    }

    @Override
    public ResponseCustom delete(Long id) {
        Classe existingClasse = classeRepository.findById(id).orElse(null);
        if (existingClasse == null) {
            throw new NoSuchElementExistsException("No classe present with id " + id);
        } else {
            classeRepository.delete(existingClasse);
            return new ResponseCustom(HttpStatus.OK.value(), "Classe deleted successfully");
        }
    }
}
