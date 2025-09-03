package com.wemi.IceTrack.services;

import com.wemi.IceTrack.entity.Session;
import com.wemi.IceTrack.repositories.SessionRepository;
import com.wemi.IceTrack.services.SessionService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SessionServiceTest {

    private final SessionRepository sessionRepository = mock(SessionRepository.class);
    private final SessionService sessionService = new SessionService(sessionRepository);

    @Test
    void saveSession_shouldReturnSavedSession() {
        Session session = new Session();
        when(sessionRepository.save(session)).thenReturn(session);

        Session result = sessionService.saveSession(session);

        assertEquals(session, result);
        verify(sessionRepository).save(session);
    }

    @Test
    void getSession_shouldReturnSessionIfFound() {
        Session session = new Session();
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));

        Session result = sessionService.getSession(1L);

        assertEquals(session, result);
        verify(sessionRepository).findById(1L);
    }

    @Test
    void getSession_shouldThrowIfNotFound() {
        when(sessionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> sessionService.getSession(1L));
    }
}