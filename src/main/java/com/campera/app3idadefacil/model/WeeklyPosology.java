package com.campera.app3idadefacil.model;

import com.campera.app3idadefacil.model.datatransfer.form.WeeklyPosologyForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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

    @OneToMany(mappedBy = "weeklyPosology") @Getter
    List<WeeklyPosologyDateTime> weeklyPosologyDateTimes = new ArrayList<>();

    @Column(name = "start_date_time")
    @Getter @Setter
    LocalDateTime startDateTime;
    @Column(name = "end_date_time")
    @Getter @Setter
    LocalDateTime endDateTime;
    public  WeeklyPosology(WeeklyPosologyForm form){
        this.startDateTime = form.getStartDateTime();
        this.endDateTime = form.getEndDateTime();
        this.weeklyPosologyDateTimes = form.getWeeklyPosologyDateTimes().stream().map(WeeklyPosologyDateTime::new)
                .collect(Collectors.toList());
        this.weeklyPosologyDateTimes.stream().forEach(date -> date.setWeeklyPosology(this));
    }
}
