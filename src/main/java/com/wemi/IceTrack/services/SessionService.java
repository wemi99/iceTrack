package com.wemi.IceTrack.services;

import com.wemi.IceTrack.entity.Session;
import com.wemi.IceTrack.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session saveSession(Session session) {
        return sessionRepository.save(session);
    }

    public Session getSession(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found"));
    }
}
