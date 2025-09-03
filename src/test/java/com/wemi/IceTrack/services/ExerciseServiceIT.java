package com.wemi.IceTrack.services;

import com.wemi.IceTrack.entity.Exercise;
import com.wemi.IceTrack.repositories.ExerciseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ExerciseServiceIT {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Test
    void saveExercise_shouldPersistExercise() {
        Exercise exercise = new Exercise();
        // set properties if needed

        Exercise saved = exerciseService.saveExercise(exercise);

        assertNotNull(saved.getId());
        assertTrue(exerciseRepository.findById(saved.getId()).isPresent());
    }

    @Test
    void getExercise_shouldReturnPersistedExercise() {
        Exercise exercise = new Exercise();
        // set properties if needed
        Exercise saved = exerciseRepository.save(exercise);

        Exercise found = exerciseService.getExercise(saved.getId());

        assertEquals(saved.getId(), found.getId());
    }

    @Test
    void getExercise_shouldThrowIfNotFound() {
        assertThrows(RuntimeException.class, () -> exerciseService.getExercise(999L));
    }
}