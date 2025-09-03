package com.wemi.IceTrack.controllers;

import com.wemi.IceTrack.entity.Combination;
import com.wemi.IceTrack.services.CombinationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/iceTrack/combinations")
public class CombinationController {
    private final CombinationService combinationService;

    public CombinationController(CombinationService combinationService) {
        this.combinationService = combinationService;
    }

    @PostMapping
    public Combination createCombination(@RequestBody Combination combination) {
        return combinationService.saveCombination(combination);
    }

    @GetMapping("/{id}")
    public Combination getCombination(@PathVariable Long id) {
        return combinationService.getCombination(id);
    }
}

