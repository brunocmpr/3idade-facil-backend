package com.campera.app3idadefacil.model;

import com.campera.app3idadefacil.model.datatransfer.form.UniformPosologyForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    @Column(name = "start_date_time") @Getter @Setter
    LocalDateTime startDateTime;
    @Column(name = "end_date_time") @Getter @Setter
    LocalDateTime endDateTime;
    @Column(name = "time_length") @Getter @Setter
    Integer timeLength;
    @Enumerated(EnumType.STRING) @Column(name = "time_unit") @Getter @Setter
    TimeUnit timeUnit;

    public UniformPosology(UniformPosologyForm form){
        this.startDateTime = form.getStartDateTime();
        this.endDateTime = form.getEndDateTime();
        this.timeLength = form.getTimeLength();
        this.timeUnit = form.getTimeUnit();
    }
}
