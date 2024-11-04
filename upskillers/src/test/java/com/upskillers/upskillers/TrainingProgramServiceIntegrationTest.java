package com.upskillers.upskillers;

import com.upskillers.upskillers.entity.TrainingProgram;
import com.upskillers.upskillers.exceptions.NoSuchElementExistsException;
import com.upskillers.upskillers.service.TrainingProgramService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class TrainingProgramServiceIntegrationTest {

    @Autowired private TrainingProgramService trainingProgramService;

    @Test
    void testAddAndRetrieveProgram() {
        TrainingProgram program = new TrainingProgram();
        program.setTitle("Spring Basics");

        trainingProgramService.add(program);

        TrainingProgram retrievedProgram = trainingProgramService.get(program.getId());
        assertEquals("Spring Basics", retrievedProgram.getTitle());
    }

    @Test
    void testUpdateProgram() {
        TrainingProgram program = new TrainingProgram();
        program.setTitle("Spring Basics");

        trainingProgramService.add(program);
        program.setTitle("Advanced Spring");

        trainingProgramService.update(program);
        TrainingProgram updatedProgram = trainingProgramService.get(program.getId());

        assertEquals("Advanced Spring", updatedProgram.getTitle());
    }

    @Test
    void testDeleteProgram() {
        TrainingProgram program = new TrainingProgram();
        program.setTitle("Spring Basics");

        trainingProgramService.add(program);
        Long id = program.getId();
        
        trainingProgramService.delete(id);
        
        assertThrows(NoSuchElementExistsException.class, () -> trainingProgramService.get(id));
    }
}
