package com.campera.app3idadefacil.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;

@Embeddable @Data
public class WeeklyPosologyDateTimeId implements Serializable {
    @Column(name = "drug_id")
    private Long drugId;
    @Column(name = "day")
    private DayOfWeek dayOfWeek;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
