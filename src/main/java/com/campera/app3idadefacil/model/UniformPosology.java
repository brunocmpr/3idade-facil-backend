package com.campera.app3idadefacil.model;

import com.campera.app3idadefacil.model.datatransfer.form.UniformPosologyForm;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@NoArgsConstructor
@Table(name = "uniform_posology")
public class UniformPosology implements Serializable {
    @Id
    @Column(name = "plan_id") @Getter @Setter
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "plan_id") @Getter @Setter
    private DrugPlan drugPlan;

    //TODO refactor for ZonedDateTime
    @Column(name = "start_date_time") @Getter @Setter
    LocalDateTime startDatetime;
    @Column(name = "end_date_time") @Getter @Setter
    LocalDateTime endDatetime;
    @Column(name = "time_length") @Getter @Setter
    Integer timeLength;
    @Enumerated(EnumType.STRING) @Column(name = "time_unit") @Getter @Setter
    TimeUnit timeUnit;

    public UniformPosology(UniformPosologyForm form){
        this.startDatetime = form.getStartDate();
        this.endDatetime = form.getEndDate();
        this.timeLength = form.getTimeLength();
        this.timeUnit = form.getTimeUnit();
    }
}
