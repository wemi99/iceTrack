package com.wemi.IceTrack.services;

import com.wemi.IceTrack.entity.Combination;
import com.wemi.IceTrack.entity.Jump;
import com.wemi.IceTrack.enums.ElementType;
import com.wemi.IceTrack.enums.JumpType;
import com.wemi.IceTrack.repositories.CombinationRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class CombinationServiceIT {

    @Autowired
    private CombinationService combinationService;

    @Autowired
    private CombinationRepository combinationRepository;

    private Combination combination;

    @BeforeEach
    void setUp() {
        combination = new Combination();
        combination.setType(ElementType.JUMP);

        List<Jump> jumps = new java.util.ArrayList<>();

        Jump lutz = new Jump();
        lutz.setName("fave jump");
        lutz.setRotations(3);
        lutz.setPrerotated(false);
        lutz.setUnderRotated(false);
        lutz.setJumpType(JumpType.LUTZ);

        Jump toeLoop = new Jump();
        toeLoop.setName("second fave jump");
        toeLoop.setRotations(2);
        toeLoop.setPrerotated(false);
        toeLoop.setUnderRotated(false);
        toeLoop.setJumpType(JumpType.TOE_LOOP);

        jumps.add(lutz);
        jumps.add(toeLoop);
        combination.setCombinationName("Lutz-Toe Loop");
        combination.setJumps(jumps);
    }

    @Test
    void saveCombination_shouldPersistCombination() {
        // set properties if needed

        Combination savedCombination = combinationService.saveCombination(combination);

        assertNotNull(savedCombination.getElementId());
        assertTrue(combinationRepository.findById(savedCombination.getElementId()).isPresent());
        assertEquals(combination.getCombinationName(), savedCombination.getCombinationName());
        assertEquals(combination.getType(), savedCombination.getType());
        assertEquals(combination.getJumps().size(), savedCombination.getJumps().size());

        assertEquals(combination.getJumps().get(0).getName(), savedCombination.getJumps().get(0).getName());
        assertEquals(combination.getJumps().get(0).getRotations(), savedCombination.getJumps().get(0).getRotations());
        assertEquals(combination.getJumps().get(0).getJumpType(), savedCombination.getJumps().get(0).getJumpType());
        assertEquals(combination.getJumps().get(0).getPrerotated(), savedCombination.getJumps().get(0).getPrerotated());
        assertEquals(combination.getJumps().get(0).getUnderRotated(), savedCombination.getJumps().get(0).getUnderRotated());

        assertEquals(combination.getJumps().get(1).getName(), savedCombination.getJumps().get(1).getName());
        assertEquals(combination.getJumps().get(1).getRotations(), savedCombination.getJumps().get(1).getRotations());
        assertEquals(combination.getJumps().get(1).getJumpType(), savedCombination.getJumps().get(1).getJumpType());
        assertEquals(combination.getJumps().get(1).getPrerotated(), savedCombination.getJumps().get(1).getPrerotated());
        assertEquals(combination.getJumps().get(1).getUnderRotated(), savedCombination.getJumps().get(1).getUnderRotated());

    }

    @Test
    void getCombination_shouldReturnPersistedCombination() {

        Combination savedCombination = combinationRepository.save(combination);

        Combination found = combinationService.getCombination(savedCombination.getElementId());

        assertEquals(savedCombination.getElementId(), found.getElementId());
    }

    @Test
    void getCombination_shouldThrowIfNotFound() {
        assertThrows(RuntimeException.class, () -> combinationService.getCombination(999L));
    }
}