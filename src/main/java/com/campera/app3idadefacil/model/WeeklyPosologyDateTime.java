package com.campera.app3idadefacil.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.OffsetTime;

@Entity @Data
@Table(name = "weekly_posology_date_time")
public class WeeklyPosologyDateTime {
    @EmbeddedId
    private WeeklyPosologyDateTimeId id;

    @ManyToOne
    @MapsId("drug_id")
    private WeeklyPosology weeklyPosology;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name = "time")
    private OffsetTime offsetTime;
}