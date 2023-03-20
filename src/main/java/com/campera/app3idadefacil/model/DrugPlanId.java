package com.campera.app3idadefacil.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Embeddable @Data
public class DrugPlanId implements Serializable {
    private static final long serialVersionUID = -1316081994949399480L;
    @Column(name = "drug_id")
    private Long drugId;
    @Column(name = "patient_id")
    private Long patientId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long plan_id;
}
