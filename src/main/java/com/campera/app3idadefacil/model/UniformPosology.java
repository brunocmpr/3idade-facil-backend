package com.campera.app3idadefacil.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "uniform_posology")
public class UniformPosology implements Serializable {
    @Id
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "drug_id", referencedColumnName = "drug_id"),
            @JoinColumn(name = "patient_id", referencedColumnName = "patient_id"),
            @JoinColumn(name = "plan_id", referencedColumnName = "plan_id")
    })
    @Getter @Setter
    private DrugPlan drugPlan;

    @Column(name = "start_date_time") @Getter @Setter
    LocalDateTime startDatetime;
    @Column(name = "end_date_time") @Getter @Setter
    LocalDateTime endDatetime;
    @Column(name = "time_length") @Getter @Setter
    Integer timeLength;
    @Enumerated(EnumType.STRING) @Column(name = "time_unit")
    TimeUnit timeUnit;
}
