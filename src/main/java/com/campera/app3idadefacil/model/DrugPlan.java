package com.campera.app3idadefacil.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drug_plan")
public class DrugPlan {
    @EmbeddedId @Getter @Setter
    private DrugPlanId id;
    @ManyToOne
    @MapsId("drug_id") @Getter
    private Drug drug;
    @ManyToOne
    @MapsId("patient_id") @Getter
    private Patient patient;

    @Column(name = "strength") @Getter @Setter
    private String strength;

    @Enumerated(EnumType.STRING)
    @Column(name = "posologyType") @Getter @Setter
    private PosologyType posologyType;
    @OneToOne(mappedBy = "drugPlan") @Getter @Setter
    private UniformPosology uniformPosology;
    @OneToOne(mappedBy = "drugPlan") @Getter @Setter
    private WeeklyPosology weeklyPosology;
    @OneToMany(mappedBy = "drugPlan") @Getter @Setter
    private List<CustomPosology> customPosologies;
}
