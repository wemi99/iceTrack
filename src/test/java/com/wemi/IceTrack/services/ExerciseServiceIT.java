package com.wemi.IceTrack.services;

import com.wemi.IceTrack.entity.Exercise;
import com.wemi.IceTrack.entity.BaseSkill;
import com.wemi.IceTrack.enums.Direction;
import com.wemi.IceTrack.enums.EdgeType;
import com.wemi.IceTrack.enums.ElementType;
import com.wemi.IceTrack.enums.Foot;
import com.wemi.IceTrack.repositories.ExerciseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ExerciseServiceIT {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseRepository exerciseRepository;
    
    private Exercise exercise;
    
    @BeforeEach
    void setUp(){
        exercise = new Exercise();
        exercise.setName("Basic Edges");
        exercise.setType(ElementType.SKILLS);
        
        BaseSkill skill1 = new BaseSkill();
        skill1.setDirection(Direction.FORWARD);

        BaseSkill skill2 = new BaseSkill();
        skill2.setDirection(Direction.FORWARD);
        
        List<BaseSkill> skills = new java.util.ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);
        exercise.setSkills(skills);
        
    }

    @Test
    void saveExercise_shouldPersistExercise() {

        Exercise saved = exerciseService.saveExercise(exercise);

        assertNotNull(saved.getElementId());
        assertTrue(exerciseRepository.findById(saved.getElementId()).isPresent());
        
        assertEquals(exercise.getName(), saved.getName());
        assertEquals(exercise.getType(), saved.getType());
        
        assertEquals(exercise.getSkills().get(0).getDirection(), saved.getSkills().get(0).getDirection());
        assertEquals(exercise.getSkills().get(1).getDirection(), saved.getSkills().get(1).getDirection());

    }

    @Test
    void getExercise_shouldReturnPersistedExercise() {

        Exercise saved = exerciseRepository.save(exercise);
        Exercise found = exerciseService.getExercise(saved.getElementId());

        assertEquals(saved.getElementId(), found.getElementId());
    }

    @Test
    void getExercise_shouldThrowIfNotFound() {
        assertThrows(RuntimeException.class, () -> exerciseService.getExercise(999L));
    }
}