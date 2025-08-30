package com.wemi.IceTrack.entity;

import com.wemi.IceTrack.enums.EdgeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("EDGE")
public class Edge extends SkatingSkill {
    @Enumerated(EnumType.STRING)
    private EdgeType type; // INSIDE, OUTSIDE
}
