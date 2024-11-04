package com.upskillers.upskillers;

import com.upskillers.upskillers.entity.TrainingProgram;
import com.upskillers.upskillers.exceptions.NoSuchElementExistsException;
import com.upskillers.upskillers.repository.TrainingProgramRepository;
import com.upskillers.upskillers.service.TrainingProgramService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.NoSuchElementException;
import java.util.Optional;

class TrainingProgramServiceTest {

    @Mock 
    private TrainingProgramRepository trainingProgramRepository;

    @Mock 
    private ModelMapper modelMapper;

    @InjectMocks 
    private TrainingProgramService trainingProgramService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetExistingProgram() {
        TrainingProgram program = new TrainingProgram();
        program.setId(1L);
        when(trainingProgramRepository.findById(1L)).thenReturn(Optional.of(program));

        TrainingProgram result = trainingProgramService.get(1L);

        assertEquals(program, result);
    }

    @Test
    void testGetNonExistingProgram() {
        when(trainingProgramRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementExistsException.class, () -> trainingProgramService.get(1L));
    }

    @Test
    void testAddNewProgram() {
        TrainingProgram program = new TrainingProgram();
        program.setTitle("Java Basics");
        when(trainingProgramRepository.findByTitle("Java Basics")).thenReturn(null);

        assertDoesNotThrow(() -> trainingProgramService.add(program));
        verify(trainingProgramRepository, times(1)).save(program);
    }


    @Test
    void testUpdateExistingProgram() {
        TrainingProgram program = new TrainingProgram();
        program.setId(1L);
        program.setTitle("Java Basics");

        TrainingProgram updatedProgram = new TrainingProgram();
        updatedProgram.setId(1L);
        updatedProgram.setTitle("Advanced Java");

        when(trainingProgramRepository.findById(1L)).thenReturn(Optional.of(program));

        trainingProgramService.update(updatedProgram);

        verify(trainingProgramRepository).save(updatedProgram);
        assertEquals("Advanced Java", updatedProgram.getTitle());
    }

    @Test
    void testUpdateNonExistingProgram() {
        TrainingProgram program = new TrainingProgram();
        program.setId(1L);
        when(trainingProgramRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> trainingProgramService.update(program));
    }

    @Test
    void testDeleteExistingProgram() {
        TrainingProgram program = new TrainingProgram();
        program.setId(1L);
        when(trainingProgramRepository.findById(1L)).thenReturn(Optional.of(program));

        trainingProgramService.delete(1L);

        verify(trainingProgramRepository).delete(program);
    }

    @Test
    void testDeleteNonExistingProgram() {
        when(trainingProgramRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> trainingProgramService.delete(1L));
    }
}
