package com.wemi.IceTrack.controllers;

import com.wemi.IceTrack.entity.Session;
import com.wemi.IceTrack.services.SessionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/iceTrack/sessions")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public Session createSession(@RequestBody Session session) {
        return sessionService.saveSession(session);
    }

    @GetMapping("/{id}")
    public Session getSession(@PathVariable Long id) {
        return sessionService.getSession(id);
    }
}

