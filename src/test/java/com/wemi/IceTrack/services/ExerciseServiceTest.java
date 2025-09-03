package com.wemi.IceTrack.services;

import com.wemi.IceTrack.entity.Exercise;
import com.wemi.IceTrack.repositories.ExerciseRepository;
import com.wemi.IceTrack.services.ExerciseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExerciseServiceTest {

    private final ExerciseRepository exerciseRepository = mock(ExerciseRepository.class);
    private final ExerciseService exerciseService = new ExerciseService(exerciseRepository);

    @Test
    void whenSaveExercise_thenReturnSavedExercise() {
        Exercise exercise = new Exercise();
        when(exerciseRepository.save(exercise)).thenReturn(exercise);

        Exercise result = exerciseService.saveExercise(exercise);

        assertEquals(exercise, result);
        verify(exerciseRepository).save(exercise);
    }

    @Test
    void getExercise_shouldReturnExerciseIfFound() {
        Exercise exercise = new Exercise();
        when(exerciseRepository.findById(1L)).thenReturn(Optional.of(exercise));

        Exercise result = exerciseService.getExercise(1L);

        assertEquals(exercise, result);
        verify(exerciseRepository).findById(1L);
    }

    @Test
    void getExercise_shouldThrowIfNotFound() {
        when(exerciseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> exerciseService.getExercise(1L));
    }
}