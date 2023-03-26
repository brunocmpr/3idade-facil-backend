package com.campera.app3idadefacil.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "custom_posology", uniqueConstraints = @UniqueConstraint(columnNames = {"drug_id", "zoned_date_time"}))
public class CustomPosology {
    @EmbeddedId @Getter @Setter
    private CustomPosologyId id;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "drug_id", referencedColumnName = "drug_id", insertable = false, updatable = false),
        @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", insertable = false, updatable = false),
        @JoinColumn(name = "plan_id", referencedColumnName = "plan_id", insertable = false, updatable = false)
    })
    @Getter @Setter
    private DrugPlan drugPlan;

    @Column(name = "zoned_date_time", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    @Getter @Setter
    ZonedDateTime zonedDateTime;
}


