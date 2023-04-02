package com.campera.app3idadefacil.model;

import com.campera.app3idadefacil.model.datatransfer.form.WeeklyPosologyDateTimeForm;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.OffsetTime;

@Entity
@Table(name = "weekly_posology_date_time")
public class WeeklyPosologyDateTime {
    @EmbeddedId @Getter @Setter
    private WeeklyPosologyDateTimeId id;

    @ManyToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "plan_id", insertable = false, updatable = false)
    @Getter @Setter
    private WeeklyPosology weeklyPosology;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private DayOfWeek dayOfWeek;

    @Column(name = "time")
    @Getter @Setter
    private OffsetTime offsetTime;

    public WeeklyPosologyDateTime(WeeklyPosologyDateTimeForm form){
        this.dayOfWeek = form.getDayOfWeek();
        this.offsetTime = form.getOffsetTime();
    }
}