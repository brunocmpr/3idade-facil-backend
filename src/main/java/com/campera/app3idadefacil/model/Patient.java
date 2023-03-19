package com.campera.app3idadefacil.model;

import lombok.Data;

import javax.persistence.*;

@Entity @Data
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private AppUser admin;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "nickname")
    private String nickname;

    public Patient(AppUser admin, String firstName, String lastName, String nickname) {
        this.admin = admin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
    }
}
