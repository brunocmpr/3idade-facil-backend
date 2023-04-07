package com.campera.app3idadefacil.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

//@Data
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomPosologyId implements Serializable {
    private Long id;
    private Long planId;
}