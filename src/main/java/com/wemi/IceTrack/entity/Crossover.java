package com.wemi.IceTrack.entity;

import com.wemi.IceTrack.enums.Direction;
import com.wemi.IceTrack.enums.Foot;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("CROSSOVER")
@Getter
@Setter
public class Crossover extends BaseSkill {

    private Boolean progressive;

    @Enumerated(EnumType.STRING)
    private Direction direction;

    @Enumerated(EnumType.STRING)
    private Foot startingFoot;
}