package com.campera.app3idadefacil.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;

@Embeddable @Data
public class WeeklyPosologyDateTimeId implements Serializable {
    @Column(name = "drug_id") @Getter @Setter
    private Long drugId;
    @Column(name = "patient_id") @Getter @Setter
    private Long patientId;
    @Column(name = "plan_id") @Getter @Setter
    private Long planId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
