package com.wemi.IceTrack.services;

import com.wemi.IceTrack.entity.Exercise;
import com.wemi.IceTrack.repositories.ExerciseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public Exercise saveExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Exercise getExercise(Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));
    }

    public long countExercises() {
        return exerciseRepository.count();
    }
}
