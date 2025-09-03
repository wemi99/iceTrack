package com.wemi.IceTrack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Session {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;
    private String location;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Attempt> attempts;

}
