package com.wemi.IceTrack.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wemi.IceTrack.entity.Exercise;
import com.wemi.IceTrack.entity.SkatingSkill;
import com.wemi.IceTrack.entity.Turn;
import com.wemi.IceTrack.enums.Direction;
import com.wemi.IceTrack.enums.EdgeType;
import com.wemi.IceTrack.enums.Foot;
import com.wemi.IceTrack.enums.TurnType;
import com.wemi.IceTrack.services.ExerciseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

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

    Exercise exercise;


    @BeforeEach
    void setUp() {
        exercise = new Exercise();
        exercise.setName("Inside Rocker Drill");
        exercise.setElementId(1L);

        List<SkatingSkill> skills = new java.util.ArrayList<>();
        Turn skill1 = new Turn();
        skill1.setEdgeType(EdgeType.INSIDE);
        skill1.setDirection(Direction.FORWARD);
        skill1.setFoot(Foot.LEFT);
        skill1.setTurnType(TurnType.ROCKER);
        skills.add(skill1);

        SkatingSkill skill2 = new SkatingSkill();
        skill2.setDirection(Direction.BACKWARD);
        skill2.setFoot(Foot.RIGHT);
        skill2.setEdgeType(EdgeType.INSIDE);
        skills.add(skill2);

        Turn skill3 = new Turn();
        skill3.setEdgeType(EdgeType.INSIDE);
        skill3.setDirection(Direction.BACKWARD);
        skill3.setFoot(Foot.RIGHT);
        skill3.setTurnType(TurnType.ROCKER);
        skills.add(skill3);

        exercise.setSkills(skills);
    }

    @Test
    void createExercise_shouldReturnCreatedExercise() throws Exception {

        Mockito.when(exerciseService.saveExercise(any(Exercise.class))).thenReturn(exercise);

        mockMvc.perform(post("/iceTrack/exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(exercise)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.elementId").value(1L));
    }

    @Test
    void getExercise_shouldReturnExercise() throws Exception {

        Mockito.when(exerciseService.getExercise(1L)).thenReturn(exercise);

        mockMvc.perform(get("/iceTrack/exercises/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.elementId").value(1L));
    }
}