package com.wemi.IceTrack.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Exercise extends Element{

    private String name; // e.g. "Rocker–Counter Drill", "Forward Outside Edges"

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<BaseSkill> skills;
}
