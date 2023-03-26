package com.campera.app3idadefacil.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @NoArgsConstructor
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") @Getter
    @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "admin_id") @Getter @Setter
    private AppUser admin;

    @Column(name = "first_name") @Getter @Setter
    private String firstName;
    @Column(name = "last_name") @Getter @Setter
    private String lastName;
    @Column(name = "nickname") @Getter @Setter
    private String nickname;

    public Patient(AppUser admin, String firstName, String lastName, String nickname) {
        this.admin = admin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
    }
}
