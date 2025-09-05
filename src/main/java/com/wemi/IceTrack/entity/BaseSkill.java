package com.wemi.IceTrack.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.wemi.IceTrack.enums.Direction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "skill_type")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "skillType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Turn.class, name = "TURN"),
        @JsonSubTypes.Type(value = Crossover.class, name = "CROSSOVER"),
        @JsonSubTypes.Type(value = Edge.class, name = "EDGE")
})
@Getter
@Setter
public class BaseSkill {

    @Id
    @GeneratedValue
    private Long skillId;

    @Enumerated(EnumType.STRING)
    private Direction direction;
}