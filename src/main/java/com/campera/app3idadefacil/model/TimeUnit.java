package com.campera.app3idadefacil.model;

public enum TimeUnit {
    MINUTE("MINUTE"),
    HOUR("HOUR"),
    DAY("DAY"),
    WEEK("WEEK");

    private final String name;
    TimeUnit(final String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
