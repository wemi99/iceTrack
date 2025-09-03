package com.wemi.IceTrack.entity;

import com.wemi.IceTrack.enums.TurnType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("TURN")
public class Turn extends SkatingSkill {
    @Enumerated(EnumType.STRING)
    private TurnType turnType; // THREE_TURN, COUNTER, ROCKER, BRACKET
}