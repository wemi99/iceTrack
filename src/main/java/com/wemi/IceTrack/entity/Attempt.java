package com.wemi.IceTrack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Attempt {
    @Id
    @GeneratedValue
    private Long attemptId;

    private String name;
    private Boolean successful;
    private String notes;

    @ManyToOne(cascade = CascadeType.ALL)
    private Session session;

    @ManyToOne(cascade = CascadeType.ALL)
    private Element element;
}