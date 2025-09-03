package com.wemi.IceTrack.entity;

import com.wemi.IceTrack.enums.ElementType;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Element {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private ElementType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
