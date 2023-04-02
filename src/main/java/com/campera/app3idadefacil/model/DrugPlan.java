package com.campera.app3idadefacil.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drug_plan")
@NoArgsConstructor
public class DrugPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") @Getter @Setter
    private Long id;
    @ManyToOne
    @JoinColumn(name = "drug_id", insertable = false, updatable = false)
    @Getter @Setter
    private Drug drug;
    @ManyToOne
    @JoinColumn(name = "patient_id", insertable = false, updatable = false)
    @Getter @Setter
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
