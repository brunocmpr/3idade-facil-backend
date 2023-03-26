package com.campera.app3idadefacil.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "drug")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") @Getter @Setter
    private Long id;
    @ManyToOne
    @JoinColumn(name = "caretaker_id") @Getter @Setter
    private AppUser caretaker;
    @Column(name = "name") @Getter @Setter
    private String name;
}
