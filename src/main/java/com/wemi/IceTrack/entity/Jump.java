package com.wemi.IceTrack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Jump extends Element {

    @ManyToOne
    @JoinColumn(name = "combination_id")
    private Combination combination;
    private Integer rotations;     // 1, 2, 3, 4
    private Boolean prerotated;
    private Boolean underRotated;
}
