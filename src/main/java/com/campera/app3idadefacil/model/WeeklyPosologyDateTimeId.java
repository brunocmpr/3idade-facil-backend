package com.campera.app3idadefacil.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;

@Embeddable @Data
public class WeeklyPosologyDateTimeId implements Serializable {
    @Column(name = "drug_plan_id")
    private Long drugPlanId;
    @Column(name = "day")
    private DayOfWeek dayOfWeek;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
