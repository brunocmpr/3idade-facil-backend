package com.campera.app3idadefacil.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;

@Embeddable
public class WeeklyPosologyDateTimeId implements Serializable {
    @Column(name = "drug_id")
    private Long drugId;
    @Column(name = "patient_id")
    private Long patientId;
    @Column(name = "plan_id")
    private Long planId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") @Getter @Setter
    private Long id;
}
