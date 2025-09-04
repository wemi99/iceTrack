package com.wemi.IceTrack.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wemi.IceTrack.entity.Combination;
import com.wemi.IceTrack.services.CombinationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

@WebMvcTest(CombinationController.class)
class CombinationControllerTest {

    @TestConfiguration
    static class MockConfig {
        @Bean
        public CombinationService combinationService() {
            return Mockito.mock(CombinationService.class);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CombinationService combinationService;

    @Autowired
    private ObjectMapper objectMapper;

    Combination combination;

    @BeforeEach
    void setUp() {
        combination = new Combination();
        combination.setCombinationName("Sample Combination");
        combination.setElementId(1L);

        // Add more setup as needed
    }

    @Test
    void createCombination_shouldReturnCreatedCombination() throws Exception {
        Mockito.when(combinationService.saveCombination(any(Combination.class))).thenReturn(combination);

        mockMvc.perform(post("/iceTrack/combinations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(combination)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.elementId").value(1L));
    }

    @Test
    void getCombination_shouldReturnCombination() throws Exception {
        Mockito.when(combinationService.getCombination(1L)).thenReturn(combination);

        mockMvc.perform(get("/iceTrack/combinations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.elementId").value(1L));
    }
}
