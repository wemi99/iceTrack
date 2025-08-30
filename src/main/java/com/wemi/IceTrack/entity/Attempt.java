package com.wemi.IceTrack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Attempt {
    @Id
    @GeneratedValue
    private Long id;

    private Boolean successful;
    private String notes;

    @ManyToOne
    private Session session;

    @ManyToOne
    private Element element;
}