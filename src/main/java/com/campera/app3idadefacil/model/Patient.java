package com.campera.app3idadefacil.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity @NoArgsConstructor
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true) @Getter @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false, updatable = false) @Getter @Setter
    private AppUser admin;

    @Column(name = "first_name", nullable = false) @Getter @Setter
    private String firstName;
    @Column(name = "last_name") @Getter @Setter
    private String lastName;
    @Column(name = "nickname") @Getter @Setter
    private String nickname;

    @OneToMany(mappedBy = "drug") @Getter @Setter
    private List<Image> images;

    public Patient(AppUser admin, String firstName, String lastName, String nickname) {
        this.admin = admin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
    }
}
