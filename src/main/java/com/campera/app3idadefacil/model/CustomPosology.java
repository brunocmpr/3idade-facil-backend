package com.campera.app3idadefacil.model;

import com.campera.app3idadefacil.model.datatransfer.form.CustomPosologyForm;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "custom_posology")
public class CustomPosology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    @Getter @Setter
    private DrugPlan drugPlan;

    @Column(name = "date_time", nullable = false)
    @Getter @Setter
    LocalDateTime dateTime;

    public CustomPosology(CustomPosologyForm form){
        this.dateTime = form.getDateTime().withSecond(0).withNano(0);
    }
}