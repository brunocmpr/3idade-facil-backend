package com.campera.app3idadefacil.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Embeddable @Data
public class CustomPosologyId implements Serializable {
    @Column(name = "drug_id")
    private Long drugId;
    @Column(name = "patient_id")
    private Long patientId;
    @Column(name = "plan_id")
    private Long planId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
