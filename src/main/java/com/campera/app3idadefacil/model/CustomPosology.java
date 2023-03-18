package com.campera.app3idadefacil.model;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity @Data
@Table(name = "custom_posology", uniqueConstraints = @UniqueConstraint(columnNames = {"drug_id", "zoned_date_time"}))
public class CustomPosology {
    @EmbeddedId
    private CustomPosologyId id;

    @ManyToOne
    @MapsId("drug_id")
    private Drug drug;

    @Column(name = "zoned_date_time", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    ZonedDateTime zonedDateTime;
}


