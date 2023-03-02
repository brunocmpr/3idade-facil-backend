package com.campera.app3idadefacil.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Authority implements GrantedAuthority{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;
    @Override
    public String getAuthority() {
        return name;
    }
}