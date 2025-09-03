package com.wemi.IceTrack.entity;

import com.wemi.IceTrack.enums.Direction;
import com.wemi.IceTrack.enums.EdgeType;
import com.wemi.IceTrack.enums.Foot;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "skill_type")
public abstract class SkatingSkill {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private EdgeType type; // INSIDE, OUTSIDE

    @Enumerated(EnumType.STRING)
    private Foot foot;

    @Enumerated(EnumType.STRING)
    private Direction direction;
}