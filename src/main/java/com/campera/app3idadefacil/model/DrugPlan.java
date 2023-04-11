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
    @Column(name = "id", nullable = false, updatable = false, unique = true) @Getter @Setter
    private Long id;
    @ManyToOne
    @JoinColumn(name = "drug_id", nullable = false)
    @Getter @Setter
    private Drug drug;
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @Getter @Setter
    private Patient patient;

    @Enumerated(EnumType.STRING)
    @Column(name = "posologyType") @Getter @Setter
    private PosologyType posologyType;
    @OneToOne(mappedBy = "drugPlan", cascade = CascadeType.ALL) @Getter @Setter
    private UniformPosology uniformPosology;
    @OneToOne(mappedBy = "drugPlan", cascade = CascadeType.ALL) @Getter @Setter
    private WeeklyPosology weeklyPosology;
    @OneToMany(mappedBy = "drugPlan", cascade = CascadeType.ALL) @Getter @Setter
    private List<CustomPosology> customPosologies;
}
