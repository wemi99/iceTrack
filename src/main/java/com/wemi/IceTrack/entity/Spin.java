package com.wemi.IceTrack.entity;

import com.wemi.IceTrack.enums.SpinPosition;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Spin extends Element {
    private Integer rotations;

    @Enumerated(EnumType.STRING)// 1, 2, 3, 4
    private SpinPosition prerotated;

    private String variation;
}
