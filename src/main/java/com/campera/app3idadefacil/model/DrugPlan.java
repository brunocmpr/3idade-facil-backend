package com.campera.app3idadefacil.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "drug_plan")
public class DrugPlan {
    @EmbeddedId
    private DrugPlanId id;
    @ManyToOne
    @MapsId("drug_id")
    private Drug drug;
    @ManyToOne
    @MapsId("patient_id")
    private Patient patient;

    @Column(name = "strength")
    private String strength;

    @Enumerated(EnumType.STRING)
    @Column(name = "posologyType")
    private PosologyType posologyType;
    @OneToOne(mappedBy = "drugPlan")
    private UniformPosology uniformPosology;
    @OneToOne(mappedBy = "drugPlan")
    private WeeklyPosology weeklyPosology;
    @OneToMany(mappedBy = "drugPlan")
    private List<CustomPosology> customPosologies;
}
