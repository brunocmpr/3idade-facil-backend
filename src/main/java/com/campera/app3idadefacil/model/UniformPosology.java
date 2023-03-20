package com.campera.app3idadefacil.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity @Data
@Table(name = "uniform_posology")
public class UniformPosology implements Serializable {
    @Id
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "drug_id", referencedColumnName = "drug_id"),
            @JoinColumn(name = "patient_id", referencedColumnName = "patient_id"),
            @JoinColumn(name = "plan_id", referencedColumnName = "plan_id")
    })
    private DrugPlan drugPlan;

    @Column(name = "start_date_time")
    LocalDateTime startDatetime;
    @Column(name = "end_date_time")
    LocalDateTime endDatetime;
    @Column(name = "time_length")
    Integer timeLength;
    @Enumerated(EnumType.STRING) @Column(name = "time_unit")
    TimeUnit timeUnit;
}
