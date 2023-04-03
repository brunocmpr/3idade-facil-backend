package com.campera.app3idadefacil.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;

@Embeddable @Data
public class WeeklyPosologyDateTimeId implements Serializable {
    @Column(name = "plan_id", nullable = false, updatable = false) @Getter @Setter
    private Long planId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
}
