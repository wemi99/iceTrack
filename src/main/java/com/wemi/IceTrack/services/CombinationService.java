package com.wemi.IceTrack.services;

import com.wemi.IceTrack.entity.Combination;
import com.wemi.IceTrack.repositories.CombinationRepository;
import org.springframework.stereotype.Service;

@Service
public class CombinationService {
    private final CombinationRepository combinationRepository;

    public CombinationService(CombinationRepository combinationRepository) {
        this.combinationRepository = combinationRepository;
    }

    public Combination saveCombination(Combination combination) {
        return combinationRepository.save(combination);
    }

    public Combination getCombination(Long id) {
        return combinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Combination not found"));
    }
}
