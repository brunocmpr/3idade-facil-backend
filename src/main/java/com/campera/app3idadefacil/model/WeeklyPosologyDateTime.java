package com.campera.app3idadefacil.model;

import com.campera.app3idadefacil.model.datatransfer.form.WeeklyPosologyDateTimeForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@Table(name = "weekly_posology_date_time")
public class WeeklyPosologyDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false) @Getter @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plan_id", updatable = false, nullable = false)
    @Getter @Setter
    private WeeklyPosology weeklyPosology;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    @Getter @Setter
    private DayOfWeek dayOfWeek;

    @Column(name = "time", nullable = false)
    @Getter @Setter
    private LocalTime time;

    public WeeklyPosologyDateTime(WeeklyPosologyDateTimeForm form){
        this.dayOfWeek = form.getDayOfWeek();
        this.time = form.getTime();
    }
}