package com.wemi.IceTrack.services;

import com.wemi.IceTrack.entity.Combination;
import com.wemi.IceTrack.repositories.CombinationRepository;
import com.wemi.IceTrack.services.CombinationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CombinationServiceTest {

    private final CombinationRepository combinationRepository = mock(CombinationRepository.class);
    private final CombinationService combinationService = new CombinationService(combinationRepository);

    @Test
    void whenSaveCombination_thenReturnSavedCombination() {
        Combination combination = new Combination();
        when(combinationRepository.save(combination)).thenReturn(combination);

        Combination result = combinationService.saveCombination(combination);

        assertEquals(combination, result);
        verify(combinationRepository).save(combination);
    }

    @Test
    void getCombination_shouldReturnCombinationIfFound() {
        Combination combination = new Combination();
        when(combinationRepository.findById(1L)).thenReturn(Optional.of(combination));

        Combination result = combinationService.getCombination(1L);

        assertEquals(combination, result);
        verify(combinationRepository).findById(1L);
    }

    @Test
    void getCombination_shouldThrowIfNotFound() {
        when(combinationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> combinationService.getCombination(1L));
    }
}