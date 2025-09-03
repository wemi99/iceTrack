package com.wemi.IceTrack.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wemi.IceTrack.entity.Exercise;
import com.wemi.IceTrack.services.ExerciseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExerciseController.class)
class ExerciseControllerTest {

    @TestConfiguration
    static class MockConfig {
        @Bean
        public ExerciseService exerciseService() {
            return Mockito.mock(ExerciseService.class);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createExercise_shouldReturnCreatedExercise() throws Exception {
        Exercise exercise = new Exercise();
        exercise.setExerciseId(1L);

        Mockito.when(exerciseService.saveExercise(any(Exercise.class))).thenReturn(exercise);

        mockMvc.perform(post("/iceTrack/exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(exercise)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void getExercise_shouldReturnExercise() throws Exception {
        Exercise exercise = new Exercise();
        exercise.setExerciseId(1L);

        Mockito.when(exerciseService.getExercise(1L)).thenReturn(exercise);

        mockMvc.perform(get("/iceTrack/exercises/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
}