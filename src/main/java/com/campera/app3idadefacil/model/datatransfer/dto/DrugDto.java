package com.campera.app3idadefacil.model.datatransfer.dto;

import com.campera.app3idadefacil.model.Drug;
import lombok.Data;

@Data
public class DrugDto {
    private Long id;
    private String name;
    private String strength;

    public DrugDto(Drug drug){
        this.id = drug.getId();
        this.name = drug.getName();
        this.strength = drug.getStrength();
    }
}
