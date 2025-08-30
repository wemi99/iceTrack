package com.wemi.IceTrack.entity;

import jakarta.persistence.Entity;

@Entity
public class Jump extends Element {
    private Integer rotations;     // 1, 2, 3, 4
    private Boolean prerotated;
    private Boolean underRotated;
}
