package com.wemi.IceTrack.entity;

import com.wemi.IceTrack.enums.ElementType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long elementId;

    @Enumerated(EnumType.STRING)
    private ElementType type;
}


