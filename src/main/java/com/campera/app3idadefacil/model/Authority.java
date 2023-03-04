package com.campera.app3idadefacil.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public enum Authority implements GrantedAuthority {
    SYSADMIN("SYSADMIN"),

    ACCOUNT_ADMIN("ACCOUNT_ADMIN"),
    CAREGIVER("CAREGIVER"),
    PATIENT("PATIENT"),
    ;

    private final String name;

    Authority(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}