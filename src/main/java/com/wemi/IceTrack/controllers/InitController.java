package com.wemi.IceTrack.controllers;

import com.wemi.IceTrack.services.ExerciseService;
import com.wemi.IceTrack.services.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/iceTrack/init")
public class InitController {

    private final ExerciseService exerciseService;
    private final SessionService sessionService;

    public InitController(ExerciseService exerciseService, SessionService sessionService) {
        this.exerciseService = exerciseService;
        this.sessionService = sessionService;
    }

    @GetMapping("/is-active")
    public ResponseEntity<String> isActive() {
        return ResponseEntity.ok("active");
    }

    @GetMapping("/db-status")
    public ResponseEntity<String> dbStatus() {
        try {
            long count = exerciseService.countExercises();
            return ResponseEntity.ok("Database is reachable. Exercise count: " + count);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Database is NOT reachable: " + e.getMessage());
        }
    }
}

