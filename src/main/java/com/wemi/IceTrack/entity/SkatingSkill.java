package com.wemi.IceTrack.entity;

import com.wemi.IceTrack.enums.Direction;
import com.wemi.IceTrack.enums.Foot;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "skill_type")
public abstract class SkatingSkill {
    @Id
    @GeneratedValue
    private Long id;

    private Foot foot;
    private Direction direction;
}