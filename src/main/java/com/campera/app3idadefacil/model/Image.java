package com.campera.app3idadefacil.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(name = "filename", nullable = false)
    @Getter @Setter
    private String filename;

    @ManyToOne
    @JoinColumn(name = "drug_id", nullable = false)
    @Getter @Setter
    private Drug drug;

    public Image(String filename) {
        this.filename = filename;
    }
}
