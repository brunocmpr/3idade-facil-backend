package com.campera.app3idadefacil.model;

import com.campera.app3idadefacil.model.datatransfer.form.CustomPosologyForm;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

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

    @Column(name = "zoned_date_time", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    @Getter @Setter
    ZonedDateTime zonedDateTime;

    public CustomPosology(CustomPosologyForm form){
        this.zonedDateTime = form.getZonedDateTime();
    }
}


