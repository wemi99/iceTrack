package com.wemi.IceTrack.entity;

import com.wemi.IceTrack.enums.SpinPosition;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spin extends Element {

    @ManyToOne
    @JoinColumn(name = "combination_id")
    private Combination combination;

    private Integer rotations;

    @Enumerated(EnumType.STRING)// 1, 2, 3, 4
    private SpinPosition position;

    private String variation;
}
