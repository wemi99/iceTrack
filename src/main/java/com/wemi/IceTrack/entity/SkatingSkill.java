package com.wemi.IceTrack.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.wemi.IceTrack.enums.Direction;
import com.wemi.IceTrack.enums.EdgeType;
import com.wemi.IceTrack.enums.Foot;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "skill_type")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "skillType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Turn.class, name = "TURN")
        // Add other subclasses here if needed
})
public class SkatingSkill {

    @Id
    @GeneratedValue
    private Long skillId;

    @Enumerated(EnumType.STRING)
    private EdgeType edgeType; // INSIDE, OUTSIDE

    @Enumerated(EnumType.STRING)
    private Foot foot;

    @Enumerated(EnumType.STRING)
    private Direction direction;
}