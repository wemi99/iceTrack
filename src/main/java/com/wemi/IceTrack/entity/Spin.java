package com.wemi.IceTrack.entity;

import jakarta.persistence.Entity;

@Entity
public class Spin extends Element {
    private Integer positionsHeld;
    private Integer durationSeconds;
}