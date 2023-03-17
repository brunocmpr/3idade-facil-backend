package com.campera.app3idadefacil.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "drug")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "caretaker_id")
    private AppUser caretaker;
    @Column(name = "name")
    private String name;
    @Column(name = "strength")
    private String strength;

    @Enumerated(EnumType.STRING)
    @Column(name = "posologyType")
    private PosologyType posologyType;

    @OneToOne(mappedBy = "drug")
    private UniformPosology uniformPosology;
}
