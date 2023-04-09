package com.campera.app3idadefacil.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "drug")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true) @Getter @Setter
    private Long id;
    @ManyToOne
    @JoinColumn(name = "caretaker_id", nullable = false, updatable = false) @Getter @Setter
    private AppUser caretaker;
    @Column(name = "name", nullable = false) @Getter @Setter
    private String name;

    @Column(name = "strength") @Getter @Setter
    private String strength;

    @OneToMany(mappedBy = "drug") @Getter @Setter
    private List<Image> images;

    public Drug(AppUser caretaker, String name, String strength) {
        this.caretaker = caretaker;
        this.name = name;
        this.strength = strength;
    }
}
