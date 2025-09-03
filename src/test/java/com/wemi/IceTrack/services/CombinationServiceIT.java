package com.wemi.IceTrack.services;

import com.wemi.IceTrack.entity.Combination;
import com.wemi.IceTrack.repositories.CombinationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class CombinationServiceIT {

    @Autowired
    private CombinationService combinationService;

    @Autowired
    private CombinationRepository combinationRepository;

    @Test
    void saveCombination_shouldPersistCombination() {
        Combination combination = new Combination();
        // set properties if needed

        Combination saved = combinationService.saveCombination(combination);

        assertNotNull(saved.getCombinationId());
        assertTrue(combinationRepository.findById(saved.getCombinationId()).isPresent());
    }

    @Test
    void getCombination_shouldReturnPersistedCombination() {
        Combination combination = new Combination();
        // set properties if needed
        Combination saved = combinationRepository.save(combination);

        Combination found = combinationService.getCombination(saved.getCombinationId());

        assertEquals(saved.getCombinationId(), found.getCombinationId());
    }

    @Test
    void getCombination_shouldThrowIfNotFound() {
        assertThrows(RuntimeException.class, () -> combinationService.getCombination(999L));
    }
}