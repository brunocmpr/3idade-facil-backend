package com.campera.app3idadefacil.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Entity @Data
@Table(name = "weekly_posology")
public class WeeklyPosology implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;

    @OneToMany(mappedBy = "drug")
    List<WeeklyPosologyDateTime> weeklyPosologyDateTime;

    @Column(name = "start_date_time", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    ZonedDateTime startDatetime;
    @Column(name = "end_date_time", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    ZonedDateTime endDatetime;
}
