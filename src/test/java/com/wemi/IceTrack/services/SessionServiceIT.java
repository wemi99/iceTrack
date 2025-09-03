package com.wemi.IceTrack.services;

import com.wemi.IceTrack.entity.Session;
import com.wemi.IceTrack.repositories.SessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class SessionServiceIT {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionRepository sessionRepository;

    @Test
    void saveSession_shouldPersistSession() {
        Session session = new Session();
        // set properties if needed

        Session saved = sessionService.saveSession(session);

        assertNotNull(saved.getSessionId());
        assertTrue(sessionRepository.findById(saved.getSessionId()).isPresent());
    }

    @Test
    void getSession_shouldReturnPersistedSession() {
        Session session = new Session();
        // set properties if needed
        Session saved = sessionRepository.save(session);

        Session found = sessionService.getSession(saved.getSessionId());

        assertEquals(saved.getSessionId(), found.getSessionId());
    }

    @Test
    void getSession_shouldThrowIfNotFound() {
        assertThrows(RuntimeException.class, () -> sessionService.getSession(999L));
    }
}