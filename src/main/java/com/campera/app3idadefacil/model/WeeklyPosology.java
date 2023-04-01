package com.campera.app3idadefacil.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "weekly_posology")
public class WeeklyPosology implements Serializable {
    @Id
    @Getter @Setter
    @Column(name = "plan_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "plan_id") @Getter @Setter
    private DrugPlan drugPlan;

    @OneToMany(mappedBy = "weeklyPosology") @Getter
    List<WeeklyPosologyDateTime> weeklyPosologyDateTime = new ArrayList<>();

    @Column(name = "start_date_time", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    @Getter @Setter
    ZonedDateTime startDatetime;
    @Column(name = "end_date_time", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    @Getter @Setter
    ZonedDateTime endDatetime;
}
