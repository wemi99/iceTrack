package com.wemi.IceTrack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Combination extends Element {

    @Id
    @GeneratedValue
    private Long combinationId;

    private String entrance;

    @OneToMany
    private List<Jump> jumps;

    @OneToMany
    private List<Spin> spins;

}