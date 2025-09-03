package com.wemi.IceTrack.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wemi.IceTrack.entity.Session;
import com.wemi.IceTrack.services.ExerciseService;
import com.wemi.IceTrack.services.SessionService;
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

    @Test
    void createSession_shouldReturnCreatedSession() throws Exception {
        Session session = new Session();
        session.setId(1L);

        Mockito.when(sessionService.saveSession(any(Session.class))).thenReturn(session);

        mockMvc.perform(post("/iceTrack/sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(session)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void getSession_shouldReturnSession() throws Exception {
        Session session = new Session();
        session.setId(1L);

        Mockito.when(sessionService.getSession(1L)).thenReturn(session);

        mockMvc.perform(get("/iceTrack/sessions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
}