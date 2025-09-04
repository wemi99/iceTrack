package com.wemi.IceTrack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Combination extends Element {

    private String combinationName;

    private String entrance;

    @OneToMany(mappedBy = "combination", cascade = CascadeType.ALL)
    private List<Jump> jumps;

    @OneToMany(mappedBy = "combination", cascade = CascadeType.ALL)
    private List<Spin> spins;

}