package com.campera.app3idadefacil.model;

import lombok.Data;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
