package com.campera.app3idadefacil.model;

public enum PosologyType {
    UNIFORM("UNIFORM"),
    WEEKLY("WEEKLY"),
    CUSTOM("CUSTOM");
    private final String name;
    PosologyType(final String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
