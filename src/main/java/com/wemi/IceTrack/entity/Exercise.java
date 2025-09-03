package com.wemi.IceTrack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Exercise {

    @Id
    @GeneratedValue
    private Long id;

    private String name; // e.g. "Rocker–Counter Drill", "Forward Outside Edges"

    @ManyToMany
    private List<SkatingSkill> skills;
}
