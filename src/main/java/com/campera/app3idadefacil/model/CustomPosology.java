package com.campera.app3idadefacil.model;

import com.campera.app3idadefacil.model.datatransfer.form.CustomPosologyForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "custom_posology")
public class CustomPosology {
    @EmbeddedId @Getter @Setter
    private CustomPosologyId id;

    @ManyToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "id", insertable = false, updatable = false)
    @Getter @Setter
    private DrugPlan drugPlan;

    @Column(name = "date_time", nullable = false)
    @Getter @Setter
    LocalDateTime dateTime;

    public CustomPosology(CustomPosologyForm form){
        this.dateTime = form.getDateTime();
    }
}


