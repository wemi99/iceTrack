package com.wemi.IceTrack.entity;

import com.wemi.IceTrack.enums.EdgeType;
import com.wemi.IceTrack.enums.Foot;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("EDGE")
@Getter
@Setter
public class Edge extends BaseSkill {

    @Enumerated(EnumType.STRING)
    private EdgeType edgeType;

    @Enumerated(EnumType.STRING)
    private Foot foot;
}
