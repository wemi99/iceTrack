package com.wemi.IceTrack.controllers;

import com.wemi.IceTrack.entity.Exercise;
import com.wemi.IceTrack.entity.Session;
import com.wemi.IceTrack.services.ExerciseService;
import com.wemi.IceTrack.services.SessionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/iceTrack/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping
    public Exercise createExercise(@RequestBody Exercise exercise) {
        return exerciseService.saveExercise(exercise);
    }

    @GetMapping("/{id}")
    public Exercise getExercise(@PathVariable Long id) {
        return exerciseService.getExercise(id);
    }
    
}
