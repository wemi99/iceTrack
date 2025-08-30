package com.wemi.IceTrack.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CROSSOVER")
public class Crossover extends SkatingSkill {
    private Boolean progressive; // normal vs. progressive crossover
}