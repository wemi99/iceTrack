package com.wemi.IceTrack.services;

import com.wemi.IceTrack.entity.Attempt;
import com.wemi.IceTrack.entity.Jump;
import com.wemi.IceTrack.entity.Session;
import com.wemi.IceTrack.enums.JumpType;
import com.wemi.IceTrack.repositories.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class SessionServiceIT {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionRepository sessionRepository;

    private Session session;

    @BeforeEach
    void setUp(){
        session = new Session();
        session.setDate(LocalDate.now());
        session.setLocation("Lee Valley Ice Rink");
        session.setDescription("Tough Monday evening session");

        Jump jumpElement = new Jump();
        jumpElement.setJumpType(JumpType.SALCHOW);
        jumpElement.setName("salchow");
        jumpElement.setRotations(2);
        jumpElement.setPrerotated(false);
        jumpElement.setUnderRotated(false);

        Attempt attempt = new Attempt();
        attempt.setName("good sal attempt");
        attempt.setSuccessful(true);
        attempt.setNotes("good height, clean landing");
        attempt.setElement(jumpElement);

        List<Attempt> attemptList = new ArrayList<>();
        attemptList.add(attempt);
        session.setAttempts(attemptList);

    }

    @Test
    void saveSession_shouldPersistSession() {
        Session saved = sessionService.saveSession(session);
        Jump expectedJump = (Jump) session.getAttempts().get(0).getElement();
        Jump savedJump = (Jump) saved.getAttempts().get(0).getElement();

        assertNotNull(saved.getSessionId());
        assertTrue(sessionRepository.findById(saved.getSessionId()).isPresent());

        assertEquals(session.getDate(), saved.getDate());
        assertEquals(session.getLocation(), saved.getLocation());
        assertEquals(session.getDescription(), saved.getDescription());
        assertEquals(session.getAttempts().size(), saved.getAttempts().size());

        assertEquals(session.getAttempts().get(0).getName(), saved.getAttempts().get(0).getName());
        assertEquals(session.getAttempts().get(0).getSuccessful(), saved.getAttempts().get(0).getSuccessful());
        assertEquals(session.getAttempts().get(0).getNotes(), saved.getAttempts().get(0).getNotes());
        assertEquals(session.getAttempts().get(0).getElement().getType(), saved.getAttempts().get(0).getElement().getType());
        assertEquals(session.getAttempts().get(0).getElement().getName(), saved.getAttempts().get(0).getElement().getName());
        assertEquals(expectedJump.getPrerotated(), savedJump.getPrerotated());
        assertEquals(expectedJump.getUnderRotated(), savedJump.getUnderRotated());
        assertEquals(expectedJump.getRotations(), savedJump.getRotations());
        assertEquals(expectedJump.getJumpType(), savedJump.getJumpType());
    }

    @Test
    void getSession_shouldReturnPersistedSession() {
        Session saved = sessionRepository.save(session);
        Session found = sessionService.getSession(saved.getSessionId());
        assertEquals(saved.getSessionId(), found.getSessionId());
    }

    @Test
    void getSession_shouldThrowIfNotFound() {
        assertThrows(RuntimeException.class, () -> sessionService.getSession(999L));
    }
}