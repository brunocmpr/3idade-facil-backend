package com.campera.app3idadefacil.model;

import com.campera.app3idadefacil.model.datatransfer.form.WeeklyPosologyForm;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
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

    //TODO rename to reflect plural
    @OneToMany(mappedBy = "weeklyPosology") @Getter
    List<WeeklyPosologyDateTime> weeklyPosologyDateTime = new ArrayList<>();

    @Column(name = "start_date_time", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    @Getter @Setter
    ZonedDateTime startDatetime;
    @Column(name = "end_date_time", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    @Getter @Setter
    ZonedDateTime endDatetime;
    public  WeeklyPosology(WeeklyPosologyForm form){
        this.startDatetime = form.getStartDate();
        this.endDatetime = form.getEndDate();
        this.weeklyPosologyDateTime = form.getWeeklyPosologyDateTimes().stream().map(WeeklyPosologyDateTime::new)
                .collect(Collectors.toList());
        this.weeklyPosologyDateTime.stream().forEach(date -> date.setWeeklyPosology(this));
    }
}
