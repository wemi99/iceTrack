package com.wemi.IceTrack.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wemi.IceTrack.entity.*;
import com.wemi.IceTrack.enums.*;
import com.wemi.IceTrack.services.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SessionController.class)
class SessionControllerTest {

    @TestConfiguration
    static class MockConfig {
        @Bean
        public SessionService sessionService() {
            return Mockito.mock(SessionService.class);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ObjectMapper objectMapper;

    Attempt jumpAttempt = new Attempt();
    Attempt spinAttempt = new Attempt();
    Attempt combinationJumpAttempt = new Attempt();
    Attempt combinationSpinAttempt = new Attempt();
    Attempt stepSequenceAttempt = new Attempt();
    List<Attempt> attemptList = new ArrayList<>();



    @BeforeEach
    void setUp(){
        attemptList.clear();
    }

    @Test
    void createSession_shouldReturnCreatedSession() throws Exception {
        Session session = new Session();
        session.setSessionId(1L);
        session.setDate(LocalDate.now());
        session.setLocation("Lee Valley Ice Rink");
        session.setDescription("Tough Monday evening session");

        Mockito.when(sessionService.saveSession(any(Session.class))).thenReturn(session);

        mockMvc.perform(post("/iceTrack/sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(session)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sessionId").value(1L));
    }

    @Test
    void getSession_shouldReturnSession() throws Exception {
        Session session = new Session();
        session.setSessionId(1L);

        Mockito.when(sessionService.getSession(1L)).thenReturn(session);

        mockMvc.perform(get("/iceTrack/sessions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sessionId").value(1L));
    }


    @BeforeEach
    void setJumpAttempt(){
        Jump doubleSalchow = new Jump();
        doubleSalchow.setType(ElementType.JUMP);
        doubleSalchow.setPrerotated(false);
        doubleSalchow.setRotations(2);
        doubleSalchow.setUnderRotated(false);
        doubleSalchow.setJumpType(JumpType.SALCHOW);
        jumpAttempt.setName("Meh double sal");
        jumpAttempt.setSuccessful(false);
        jumpAttempt.setNotes("Stepped out on landing. Maybe need to hold my core stronger in air position?");
        jumpAttempt.setElement(doubleSalchow);
        attemptList.add(jumpAttempt);
    }

    @BeforeEach
    void setSpinAttempt(){
        Spin camelSpin = new Spin();
        camelSpin.setType(ElementType.SPIN);
        camelSpin.setPosition(SpinPosition.CAMEL);
        camelSpin.setRotations(3);

        spinAttempt.setSuccessful(true);
        spinAttempt.setName("Decent camel");
        spinAttempt.setNotes("Good speed and centering, but need to work on flexibility.");
        spinAttempt.setElement(camelSpin);
        attemptList.add(spinAttempt);
    }

    @BeforeEach
    void setCombinationJumpAttempt(){
        Combination lutzToeLoopCombo = new Combination();
        lutzToeLoopCombo.setType(ElementType.JUMP);

        List<Jump> jumps = new java.util.ArrayList<>();

        Jump lutz = new Jump();
        lutz.setRotations(3);
        lutz.setPrerotated(false);
        lutz.setUnderRotated(false);
        lutz.setJumpType(JumpType.LUTZ);

        Jump toeLoop = new Jump();
        toeLoop.setRotations(2);
        toeLoop.setPrerotated(false);
        toeLoop.setUnderRotated(false);
        toeLoop.setJumpType(JumpType.TOE_LOOP);

        jumps.add(lutz);
        jumps.add(toeLoop);
        lutzToeLoopCombo.setJumps(jumps);

        combinationJumpAttempt.setSuccessful(false);
        combinationJumpAttempt.setNotes("Landed the Lutz well, but stepped out on the Toe Loop. Need to focus on quick edge change.");
        combinationJumpAttempt.setElement(lutzToeLoopCombo);
        attemptList.add(combinationJumpAttempt);
    }

    @BeforeEach
    void setCombinationSpinAttempt(){
        Combination camelComboSpin = new Combination();
        camelComboSpin.setType(ElementType.SPIN);

        List<Spin> spins = new java.util.ArrayList<>();

        Spin position1 = new Spin();
        position1.setPosition(SpinPosition.CAMEL);
        position1.setRotations(4);

        Spin position2 = new Spin();
        position2.setPosition(SpinPosition.UPRIGHT);
        position2.setRotations(4);
        position2.setVariation("Haircutter");

        Spin position3 = new Spin();
        position3.setPosition(SpinPosition.BACK_SIT);
        position3.setRotations(4);

        Spin position4 = new Spin();
        position4.setPosition(SpinPosition.BACK_UPRIGHT);
        position4.setRotations(4);
        position4.setVariation("Foot-face");

        spins.add(position1);
        spins.add(position2);
        spins.add(position3);
        spins.add(position4);
        camelComboSpin.setSpins(spins);

        combinationSpinAttempt.setSuccessful(true);
        combinationSpinAttempt.setNotes("Felt really good! Need to work on increasing speed in foot-face position.");
        combinationSpinAttempt.setElement(camelComboSpin);
        attemptList.add(combinationSpinAttempt);
    }

    @BeforeEach
    void setStepSequenceAttempt(){
        Exercise exercise = new Exercise();
        exercise.setName("Inside Rocker Drill");

        List<BaseSkill> skills = new java.util.ArrayList<>();
        Turn skill1 = new Turn();
        skill1.setEdgeType(EdgeType.INSIDE);
        skill1.setDirection(Direction.FORWARD);
        skill1.setFoot(Foot.LEFT);
        skill1.setTurnType(TurnType.ROCKER);
        skills.add(skill1);

        BaseSkill skill2 = new BaseSkill();
        skill2.setDirection(Direction.BACKWARD);
        skills.add(skill2);

        Turn skill3 = new Turn();
        skill3.setEdgeType(EdgeType.INSIDE);
        skill3.setDirection(Direction.BACKWARD);
        skill3.setFoot(Foot.RIGHT);
        skill3.setTurnType(TurnType.ROCKER);
        skills.add(skill3);

        exercise.setSkills(skills);
        stepSequenceAttempt.setElement(exercise);
        attemptList.add(stepSequenceAttempt);
    }
}