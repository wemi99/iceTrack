package com.wemi.IceTrack.entity;

import com.wemi.IceTrack.enums.JumpType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Jump extends Element {

    @ManyToOne
    @JoinColumn(name = "combination_id")
    private Combination combination;

    @Enumerated(EnumType.STRING)
    private JumpType jumpType;

    private Integer rotations;
    private Boolean prerotated;
    private Boolean underRotated;
}
