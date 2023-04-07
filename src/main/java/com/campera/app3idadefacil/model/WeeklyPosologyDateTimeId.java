package com.campera.app3idadefacil.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;

@Data @AllArgsConstructor @NoArgsConstructor
public class WeeklyPosologyDateTimeId implements Serializable {
    @Column(name = "plan_id", nullable = false, updatable = false) @Getter @Setter
    private Long planId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
}
