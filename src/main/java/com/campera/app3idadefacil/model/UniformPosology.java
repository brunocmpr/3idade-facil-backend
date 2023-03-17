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
    @JoinColumn(name = "drug_id")
    private Drug drug;

    @Column(name = "start_date_time")
    LocalDateTime startDatetime;
    @Column(name = "end_date_time")
    LocalDateTime endDatetime;
    @Column(name = "time_length")
    Integer timeLength;
    @Enumerated(EnumType.STRING) @Column(name = "time_unit")
    TimeUnit timeUnit;
}
